package br.ufal.ic.p2.bikeshare.servico;

import br.ufal.ic.p2.bikeshare.excecao.campo.EmailNuloException;
import br.ufal.ic.p2.bikeshare.excecao.campo.IdentificacaoUsuarioNulaException;
import br.ufal.ic.p2.bikeshare.excecao.campo.NomeNuloException;
import br.ufal.ic.p2.bikeshare.excecao.campo.SenhaNulaException;
import br.ufal.ic.p2.bikeshare.excecao.campo.ValorNuloException;
import br.ufal.ic.p2.bikeshare.excecao.duplicado.EmailJaCadastradoException;
import br.ufal.ic.p2.bikeshare.excecao.formato.AtributoNaoAlteravelException;
import br.ufal.ic.p2.bikeshare.excecao.formato.EmailInvalidoException;
import br.ufal.ic.p2.bikeshare.excecao.formato.ValorNaoNumericoException;
import br.ufal.ic.p2.bikeshare.excecao.naoencontrado.UsuarioNaoExisteException;
import br.ufal.ic.p2.bikeshare.fabrica.DadosDeCadastro;
import br.ufal.ic.p2.bikeshare.fabrica.FabricaDeMetodoPagamento;
import br.ufal.ic.p2.bikeshare.fabrica.FabricaDeUsuario;
import br.ufal.ic.p2.bikeshare.fabrica.MoldeDeUsuario;
import br.ufal.ic.p2.bikeshare.modelo.plano.Assinatura;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;
import br.ufal.ic.p2.bikeshare.sistema.Sistema;
import br.ufal.ic.p2.bikeshare.util.Numeros;
import br.ufal.ic.p2.bikeshare.util.Texto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class UsuarioService {
    private static final Pattern EMAIL = Pattern.compile("[^@\\s]+@[^@\\s]+");

    private final Sistema sistema;

    private final Map<String, BiConsumer<Usuario, String>> alteracoes = new HashMap<>();

    public UsuarioService(Sistema sistema) {
        this.sistema = sistema;
        alteracoes.put("nome", Usuario::setNome);
        alteracoes.put("email", (u, valor) -> u.setEmail(validarEmail(valor, u)));
        alteracoes.put("senha", Usuario::setSenha);
        alteracoes.put("plano", this::trocarPlano);
        alteracoes.put("saldo", this::recarregarSaldo);
        alteracoes.put("metodoPagamento",
                (u, valor) -> u.setMetodoPagamento(FabricaDeMetodoPagamento.doNome(valor).semDados(u)));
    }

    public String criarComDocumento(String nome, String email, String senha, String tipo, String documento) {
        return criar(nome, email, senha, tipo, fabrica -> fabrica.comDocumento(documento), this::assinaturaPadrao);
    }

    public String criarComDocumento(String nome, String email, String senha, String tipo, String documento,
                                    String plano, String mensalidade) {
        return criar(nome, email, senha, tipo, fabrica -> fabrica.comDocumento(documento),
                () -> contratar(plano, mensalidade));
    }

    public String criarComRazaoSocial(String nome, String email, String senha, String tipo, String cnpj,
                                      String razaoSocial) {
        return criar(nome, email, senha, tipo, fabrica -> fabrica.comRazaoSocial(cnpj, razaoSocial),
                this::assinaturaPadrao);
    }

    public String criarComRazaoSocial(String nome, String email, String senha, String tipo, String cnpj,
                                      String razaoSocial, String plano, String mensalidade) {
        return criar(nome, email, senha, tipo, fabrica -> fabrica.comRazaoSocial(cnpj, razaoSocial),
                () -> contratar(plano, mensalidade));
    }

    private String criar(String nome, String email, String senha, String tipo,
                         Function<FabricaDeUsuario, MoldeDeUsuario> moldar, Supplier<Assinatura> assinar) {
        DadosDeCadastro dados = validarDados(nome, email, senha);
        MoldeDeUsuario molde = moldar.apply(FabricaDeUsuario.doTipo(tipo));
        Assinatura assinatura = assinar.get();
        Usuario usuario = molde.construir(sistema.getIdsDeUsuario().proximo(), dados, assinatura);
        sistema.getUsuarios().salvar(usuario);
        return usuario.getId();
    }

    private Assinatura assinaturaPadrao() {
        return sistema.getPlanos().padrao().contratarComValorPadrao();
    }

    private Assinatura contratar(String nomePlano, String mensalidade) {
        return sistema.getPlanos().buscar(nomePlano).contratar(mensalidade);
    }

    private DadosDeCadastro validarDados(String nome, String email, String senha) {
        String nomeValido = Texto.obrigatorio(nome, NomeNuloException::new);
        String emailValido = validarEmail(email, null);
        String senhaValida = Texto.obrigatorio(senha, SenhaNulaException::new);
        return new DadosDeCadastro(nomeValido, emailValido, senhaValida);
    }

    private String validarEmail(String email, Usuario dono) {
        String valor = Texto.obrigatorio(email, EmailNuloException::new);
        if (!EMAIL.matcher(valor).matches()) {
            throw new EmailInvalidoException();
        }
        boolean emUsoPorOutro = sistema.getUsuarios()
                .primeiro(u -> u.temEmail(valor) && u != dono)
                .isPresent();
        if (emUsoPorOutro) {
            throw new EmailJaCadastradoException();
        }
        return valor;
    }

    public Usuario buscar(String id) {
        return sistema.getUsuarios().buscar(id, IdentificacaoUsuarioNulaException::new, UsuarioNaoExisteException::new);
    }

    public String getAtributo(String id, String atributo) {
        return buscar(id).getAtributo(atributo);
    }

    public String getPorEmail(String email) {
        String valor = Texto.obrigatorio(email, EmailNuloException::new);
        return sistema.getUsuarios()
                .primeiro(u -> u.temEmail(valor))
                .orElseThrow(UsuarioNaoExisteException::new)
                .getId();
    }

    public void alterar(String id, String atributo, String valor) {
        Usuario usuario = buscar(id);
        BiConsumer<Usuario, String> alteracao = alteracoes.get(atributo);
        if (alteracao == null) {
            throw new AtributoNaoAlteravelException();
        }
        alteracao.accept(usuario, valor);
    }

    public void alterarMetodoComChave(String id, String atributo, String metodo, String chave) {
        Usuario usuario = buscar(id);
        validarAtributoMetodoPagamento(atributo);
        usuario.setMetodoPagamento(FabricaDeMetodoPagamento.doNome(metodo).comChave(chave));
    }

    public void alterarMetodoComCartao(String id, String atributo, String metodo, String numero,
                                       String validade) {
        Usuario usuario = buscar(id);
        validarAtributoMetodoPagamento(atributo);
        usuario.setMetodoPagamento(FabricaDeMetodoPagamento.doNome(metodo).comCartao(numero, validade));
    }

    private void validarAtributoMetodoPagamento(String atributo) {
        if (!"metodoPagamento".equals(atributo)) {
            throw new AtributoNaoAlteravelException();
        }
    }

    private void trocarPlano(Usuario usuario, String nomePlano) {
        Assinatura nova = sistema.getPlanos().buscar(nomePlano).contratarComValorPadrao();
        if (!usuario.assinaOPlanoDe(nova)) {
            usuario.setAssinatura(nova);
        }
    }

    private void recarregarSaldo(Usuario usuario, String valorInformado) {
        usuario.validarRecargaDeSaldo();
        BigDecimal valor = Numeros.decimal(valorInformado, ValorNuloException::new, ValorNaoNumericoException::new);
        usuario.recarregarSaldo(valor);
    }

    public void remover(String id) {
        Usuario usuario = buscar(id);
        usuario.validarRemocao();
        sistema.getUsuarios().remover(usuario);
    }
}
