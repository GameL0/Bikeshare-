package br.ufal.ic.p2.bikeshare.excecao.incompativel;

public class UsuarioNaoUsaPixException extends IncompativelException {
    public UsuarioNaoUsaPixException() {
        super("Usuario nao usa pix.");
    }
}
