package br.ufal.ic.p2.bikeshare.excecao.regra;

public class AluguelJaEmAndamentoException extends RegraDeNegocioException {
    public AluguelJaEmAndamentoException() {
        super("Usuario ja possui um aluguel em andamento.");
    }
}
