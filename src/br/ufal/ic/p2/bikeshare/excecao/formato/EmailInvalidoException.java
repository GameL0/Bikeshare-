package br.ufal.ic.p2.bikeshare.excecao.formato;

public class EmailInvalidoException extends FormatoInvalidoException {
    public EmailInvalidoException() {
        super("Email invalido.");
    }
}
