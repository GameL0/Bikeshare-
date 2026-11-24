package br.ufal.ic.p2.bikeshare.excecao.formato;

public class ValorNaoPositivoException extends FormatoInvalidoException {
    public ValorNaoPositivoException() {
        super("Valor deve ser positivo.");
    }
}
