package br.ufal.ic.p2.bikeshare.excecao.formato;

public class ValidadeCartaoInvalidaException extends FormatoInvalidoException {
    public ValidadeCartaoInvalidaException() {
        super("Validade do cartao invalida.");
    }
}
