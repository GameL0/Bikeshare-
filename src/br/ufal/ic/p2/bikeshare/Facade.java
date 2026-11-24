package br.ufal.ic.p2.bikeshare;

import br.ufal.ic.p2.bikeshare.comando.ExecutorDeComandos;
import br.ufal.ic.p2.bikeshare.config.Parametros;
import br.ufal.ic.p2.bikeshare.servico.AluguelService;
import br.ufal.ic.p2.bikeshare.servico.BicicletaService;
import br.ufal.ic.p2.bikeshare.servico.EstacaoService;
import br.ufal.ic.p2.bikeshare.servico.UsuarioService;
import br.ufal.ic.p2.bikeshare.sistema.Persistencia;
import br.ufal.ic.p2.bikeshare.sistema.Sistema;

/**
 Facade
 */
public class Facade {

    private static final String AINDA_NAO_IMPLEMENTADO = "Ainda não implementado.";

    private final Parametros parametros = Parametros.carregar();
    private final Persistencia persistencia = new Persistencia();
    private final ExecutorDeComandos executor = new ExecutorDeComandos(() -> this.sistema, this::usar);

    private Sistema sistema;
    private UsuarioService usuarios;
    private EstacaoService estacoes;
    private BicicletaService bicicletas;
    private AluguelService alugueis;

    public Facade() {
        usar(persistencia.carregar().orElseGet(() -> new Sistema(parametros)));
    }

    private void usar(Sistema novoSistema) {
        sistema = novoSistema;
        sistema.aplicar(parametros);
        usuarios = new UsuarioService(sistema);
        estacoes = new EstacaoService(sistema, parametros);
        bicicletas = new BicicletaService(sistema, estacoes);
        alugueis = new AluguelService(sistema, parametros, usuarios, estacoes, bicicletas);
    }

    // =====================================================================
    // US1 - Cadastro de usuario
    // =====================================================================

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpfOuCnpj) {
        return executor.executar(() -> usuarios.criarComDocumento(nome, email, senha, tipo, cpfOuCnpj));
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj, String razaoSocial) {
        return executor.executar(() -> usuarios.criarComRazaoSocial(nome, email, senha, tipo, cnpj, razaoSocial));
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpf,
                                String plano, String mensalidade) {
        return executor.executar(() -> usuarios.criarComDocumento(nome, email, senha, tipo, cpf, plano, mensalidade));
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj,
                                String razaoSocial, String plano, String mensalidade) {
        return executor.executar(() -> usuarios.criarComRazaoSocial(nome, email, senha, tipo, cnpj, razaoSocial,
                plano, mensalidade));
    }

    // =====================================================================
    // US2 - Consulta de usuario
    // =====================================================================

    public String getAtributoUsuario(String usuario, String atributo) {
        return usuarios.getAtributo(usuario, atributo);
    }

    public String getUsuarioPorEmail(String email) {
        return usuarios.getPorEmail(email);
    }

    // =====================================================================
    // US3 - Alteracao de usuario (inclui metodo de pagamento)
    // =====================================================================

    public void alterarUsuario(String usuario, String atributo, String valor) {
        executor.executar(() -> usuarios.alterar(usuario, atributo, valor));
    }

    public void alterarUsuario(String usuario, String atributo, String valor1, String chave) {
        executor.executar(() -> usuarios.alterarMetodoComChave(usuario, atributo, valor1, chave));
    }

    public void alterarUsuario(String usuario, String atributo, String valor1, String numero, String validade) {
        executor.executar(() -> usuarios.alterarMetodoComCartao(usuario, atributo, valor1, numero, validade));
    }

    // =====================================================================
    // US4 - Remocao de usuario
    // =====================================================================

    public void removerUsuario(String usuario) {
        executor.executar(() -> usuarios.remover(usuario));
    }

    // =====================================================================
    // US5 - Cadastro de estacao
    // =====================================================================

    public String criarEstacao(String nome, String endereco, String capacidade) {
        return executor.executar(() -> estacoes.criar(nome, endereco, capacidade));
    }

    // =====================================================================
    // US6 - Gestao de bicicletas na estacao
    // =====================================================================

    public void adicionarBicicleta(String estacao, String bicicleta, String tipo) {
        executor.executar(() -> bicicletas.adicionarConvencional(estacao, bicicleta, tipo));
    }

    public void adicionarBicicleta(String estacao, String bicicleta, String tipo, String nivelBateria) {
        executor.executar(() -> bicicletas.adicionarComBateria(estacao, bicicleta, tipo, nivelBateria));
    }

    public void removerBicicleta(String estacao, String bicicleta) {
        executor.executar(() -> bicicletas.remover(estacao, bicicleta));
    }

    public String getAtributoBicicleta(String bicicleta, String atributo) {
        return bicicletas.getAtributo(bicicleta, atributo);
    }

    // =====================================================================
    // US7 - Consulta de disponibilidade
    // =====================================================================

    public String getBicicletasDisponiveis(String estacao) {
        return estacoes.getBicicletasDisponiveis(estacao);
    }

    public String getVagasDisponiveis(String estacao) {
        return estacoes.getVagasDisponiveis(estacao);
    }

    public String getEstadoBicicleta(String bicicleta) {
        return bicicletas.getEstado(bicicleta);
    }

    // =====================================================================
    // US8 - Retirada de bicicleta
    // =====================================================================

    public String retirarBicicleta(String usuario, String estacao, String bicicleta, String data) {
        return executor.executar(() -> alugueis.retirar(usuario, estacao, bicicleta, data));
    }

    // =====================================================================
    // US9 - Devolucao de bicicleta
    // =====================================================================

    public void devolverBicicleta(String aluguel, String estacao, String data) {
        executor.executar(() -> alugueis.devolver(aluguel, estacao, data));
    }

    // =====================================================================
    // US10 - Consulta de historico de alugueis
    // =====================================================================

    public String getHistoricoAlugueis(String usuario) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public String getHistoricoAlugueis(String usuario, String atributo) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public String getAtributoAluguel(String aluguel, String atributo) {
        return alugueis.getAtributo(aluguel, atributo);
    }

    // =====================================================================
    // US11/US12 - Planos e tarifacao / criacao de novos planos
    // =====================================================================

    public String criarPlano(String nome, String franquiaMinutos, String valorMinutoExcedente, String mensalidade) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US13 - Regras de bloqueio / lancamento manual de penalidade
    // =====================================================================

    public void lancaPenalidade(String usuario, String tipo, String valor, String motivo) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US14 - Lancamento e conclusao de manutencao
    // =====================================================================

    public void lancaDefeito(String bicicleta, String descricao) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public void concluiManutencao(String bicicleta) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US15 - Relatorios
    // =====================================================================

    public String getTotalFaturado(String dataInicial, String dataFinal) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US16 - Undo/redo
    // =====================================================================

    public void undo() {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public void redo() {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // Ciclo de vida do sistema
    // =====================================================================

    public void zerarSistema() {
        executor.executar(() -> usar(new Sistema(parametros)));
    }

    public void encerrarSistema() {
        persistencia.salvar(sistema);
    }
}
