package br.ufal.ic.p2.bikeshare.excecao.incompativel;

public class UsuarioNaoUsaCartaoException extends IncompativelException {
    public UsuarioNaoUsaCartaoException() {
        super("Usuario nao usa cartao.");
    }
}
