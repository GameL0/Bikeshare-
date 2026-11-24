package br.ufal.ic.p2.bikeshare.excecao.duplicado;

public class EmailJaCadastradoException extends DuplicadoException {
    public EmailJaCadastradoException() {
        super("Email ja cadastrado.");
    }
}
