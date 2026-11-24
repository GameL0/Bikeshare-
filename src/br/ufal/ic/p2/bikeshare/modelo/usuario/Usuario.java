package br.ufal.ic.p2.bikeshare.modelo.usuario;

import br.ufal.ic.p2.bikeshare.excecao.campo.EmailNuloException;
import br.ufal.ic.p2.bikeshare.excecao.campo.NomeNuloException;
import br.ufal.ic.p2.bikeshare.excecao.campo.SenhaNulaException;
import br.ufal.ic.p2.bikeshare.excecao.incompativel.UsuarioNaoEhPessoaFisicaException;
import br.ufal.ic.p2.bikeshare.excecao.incompativel.UsuarioNaoEhPessoaJuridicaException;
import br.ufal.ic.p2.bikeshare.excecao.regra.AluguelJaEmAndamentoException;
import br.ufal.ic.p2.bikeshare.excecao.regra.RemocaoComAluguelEmAndamentoException;
import br.ufal.ic.p2.bikeshare.excecao.regra.UsuarioInadimplenteException;
import br.ufal.ic.p2.bikeshare.modelo.aluguel.Aluguel;
import br.ufal.ic.p2.bikeshare.modelo.atributo.LeitorDeAtributos;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.MetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.SaldoPreCarregado;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.SemMetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.penalidade.Penalidade;
import br.ufal.ic.p2.bikeshare.modelo.plano.Assinatura;
import br.ufal.ic.p2.bikeshare.repositorio.Identificavel;
import br.ufal.ic.p2.bikeshare.util.Dinheiro;
import br.ufal.ic.p2.bikeshare.util.Texto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Usuario implements Identificavel, Serializable {
    private static final long serialVersionUID = 1L;

    private static final LeitorDeAtributos<Usuario> LEITOR = new LeitorDeAtributos<Usuario>()
            .com("nome", Usuario::getNome)
            .com("email", Usuario::getEmail)
            .com("senha", Usuario::getSenha)
            .com("tipo", Usuario::getTipo)
            .com("estado", Usuario::getEstado)
            .com("cpf", Usuario::getCpf)
            .com("cnpj", Usuario::getCnpj)
            .com("razaoSocial", Usuario::getRazaoSocial)
            .com("plano", u -> u.assinatura.getPlano().getNome())
            .com("mensalidade", u -> Dinheiro.formatar(u.assinatura.getMensalidade()))
            .com("metodoPagamento", u -> u.metodoPagamento.getNome())
            .com("numeroCartao", u -> u.metodoPagamento.getNumeroCartao())
            .com("validadeCartao", u -> u.metodoPagamento.getValidadeCartao())
            .com("chavePix", u -> u.metodoPagamento.getChavePix())
            .com("saldo", u -> Dinheiro.formatar(u.metodoPagamento.getSaldo()))
            .com("aluguelAtivo", Usuario::getIdAluguelAtivo)
            .com("totalPendencias", u -> Dinheiro.formatar(u.getTotalPendencias()));

    private final String id;
    private String nome;
    private String email;
    private String senha;
    private Assinatura assinatura;
    private MetodoPagamento metodoPagamento = new SemMetodoPagamento();
    private final SaldoPreCarregado carteira = new SaldoPreCarregado();
    private Aluguel aluguelAtivo;
    private final List<Penalidade> penalidades = new ArrayList<>();
    private final List<Aluguel> historico = new ArrayList<>();

    protected Usuario(String id, String nome, String email, String senha, Assinatura assinatura) {
        this.id = Objects.requireNonNull(id, "id");
        this.nome = nomeValido(nome);
        this.email = emailValido(email);
        this.senha = senhaValida(senha);
        this.assinatura = Objects.requireNonNull(assinatura, "assinatura");
    }

    private static String nomeValido(String nome) {
        return Texto.obrigatorio(nome, NomeNuloException::new);
    }

    private static String emailValido(String email) {
        return Texto.obrigatorio(email, EmailNuloException::new);
    }

    private static String senhaValida(String senha) {
        return Texto.obrigatorio(senha, SenhaNulaException::new);
    }

    public abstract String getTipo();

    public String getCpf() {
        throw new UsuarioNaoEhPessoaFisicaException();
    }

    public String getCnpj() {
        throw new UsuarioNaoEhPessoaJuridicaException();
    }

    public String getRazaoSocial() {
        throw new UsuarioNaoEhPessoaJuridicaException();
    }

    public String getAtributo(String atributo) {
        return LEITOR.ler(this, atributo);
    }

    @Override
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean temEmail(String outroEmail) {
        return email.equalsIgnoreCase(outroEmail);
    }

    public void setNome(String nome) {
        this.nome = nomeValido(nome);
    }

    public void setEmail(String email) {
        this.email = emailValido(email);
    }

    public void setSenha(String senha) {
        this.senha = senhaValida(senha);
    }

    public boolean assinaOPlanoDe(Assinatura outra) {
        return assinatura.ehDoMesmoPlanoQue(outra);
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = Objects.requireNonNull(assinatura, "assinatura");
    }

    public BigDecimal calcularTarifa(long minutos) {
        return assinatura.getPlano().calcularTarifa(minutos);
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = Objects.requireNonNull(metodoPagamento, "metodoPagamento");
    }

    public SaldoPreCarregado getCarteira() {
        return carteira;
    }

    public void autorizarRetirada() {
        metodoPagamento.autorizarRetirada();
    }

    public void cobrar(BigDecimal valor) {
        metodoPagamento.cobrar(valor);
    }

    public void validarRecargaDeSaldo() {
        metodoPagamento.validarRecarga();
    }

    public void recarregarSaldo(BigDecimal valor) {
        metodoPagamento.recarregar(valor);
    }

    public String getEstado() {
        return estaInadimplente() ? "inadimplente" : "ativo";
    }

    public boolean estaInadimplente() {
        return getTotalPendencias().signum() > 0;
    }

    public BigDecimal getTotalPendencias() {
        BigDecimal total = BigDecimal.ZERO;
        for (Penalidade penalidade : penalidades) {
            total = total.add(penalidade.getValor());
        }
        return total;
    }

    public void adicionarPenalidade(Penalidade penalidade) {
        penalidades.add(Objects.requireNonNull(penalidade, "penalidade"));
    }

    public void validarNovaRetirada() {
        if (estaInadimplente()) {
            throw new UsuarioInadimplenteException();
        }
        if (aluguelAtivo != null) {
            throw new AluguelJaEmAndamentoException();
        }
    }

    public void validarRemocao() {
        if (aluguelAtivo != null) {
            throw new RemocaoComAluguelEmAndamentoException();
        }
    }

    public void iniciarAluguel(Aluguel aluguel) {
        validarNovaRetirada();
        aluguelAtivo = Objects.requireNonNull(aluguel, "aluguel");
        historico.add(aluguel);
    }

    public void encerrarAluguel() {
        aluguelAtivo = null;
    }

    public String getIdAluguelAtivo() {
        return aluguelAtivo == null ? "nenhum" : aluguelAtivo.getId();
    }

    public List<Aluguel> getHistorico() {
        return List.copyOf(historico);
    }
}
