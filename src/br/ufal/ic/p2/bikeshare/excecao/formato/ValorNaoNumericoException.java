package br.ufal.ic.p2.bikeshare.excecao.formato;

public class ValorNaoNumericoException extends FormatoInvalidoException {
    public ValorNaoNumericoException() {
        super("Valor deve ser numerico.");
    }
}
