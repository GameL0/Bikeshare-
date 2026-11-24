package br.ufal.ic.p2.bikeshare.excecao.incompativel;

public class UsuarioNaoUsaSaldoException extends IncompativelException {
    public UsuarioNaoUsaSaldoException() {
        super("Usuario nao usa saldo pre-carregado.");
    }
}
