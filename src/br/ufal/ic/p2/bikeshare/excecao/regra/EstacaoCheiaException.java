package br.ufal.ic.p2.bikeshare.excecao.regra;

public class EstacaoCheiaException extends RegraDeNegocioException {
    public EstacaoCheiaException() {
        super("Estacao cheia.");
    }
}
