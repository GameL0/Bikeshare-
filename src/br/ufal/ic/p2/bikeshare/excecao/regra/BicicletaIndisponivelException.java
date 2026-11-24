package br.ufal.ic.p2.bikeshare.excecao.regra;

public class BicicletaIndisponivelException extends RegraDeNegocioException {
    public BicicletaIndisponivelException() {
        super("Bicicleta nao esta disponivel.");
    }
}
