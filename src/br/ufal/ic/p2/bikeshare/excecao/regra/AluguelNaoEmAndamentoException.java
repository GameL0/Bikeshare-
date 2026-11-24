package br.ufal.ic.p2.bikeshare.excecao.regra;

public class AluguelNaoEmAndamentoException extends RegraDeNegocioException {
    public AluguelNaoEmAndamentoException() {
        super("Aluguel nao esta em andamento.");
    }
}
