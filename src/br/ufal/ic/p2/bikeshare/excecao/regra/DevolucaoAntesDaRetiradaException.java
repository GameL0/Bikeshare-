package br.ufal.ic.p2.bikeshare.excecao.regra;

public class DevolucaoAntesDaRetiradaException extends RegraDeNegocioException {
    public DevolucaoAntesDaRetiradaException() {
        super("Data de devolucao anterior a data de retirada.");
    }
}
