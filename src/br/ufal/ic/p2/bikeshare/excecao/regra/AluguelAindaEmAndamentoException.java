package br.ufal.ic.p2.bikeshare.excecao.regra;

public class AluguelAindaEmAndamentoException extends RegraDeNegocioException {
    public AluguelAindaEmAndamentoException() {
        super("Aluguel ainda esta em andamento.");
    }
}
