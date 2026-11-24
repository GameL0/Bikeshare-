package br.ufal.ic.p2.bikeshare.excecao.regra;

public class MetodoPagamentoNaoCadastradoException extends RegraDeNegocioException {
    public MetodoPagamentoNaoCadastradoException() {
        super("Metodo de pagamento nao cadastrado.");
    }
}
