package br.ufal.ic.p2.bikeshare.excecao.regra;

public class BicicletaForaDaEstacaoException extends RegraDeNegocioException {
    public BicicletaForaDaEstacaoException() {
        super("Bicicleta nao esta nesta estacao.");
    }
}
