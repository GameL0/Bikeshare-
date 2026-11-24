package br.ufal.ic.p2.bikeshare.excecao.naoencontrado;

public class UsuarioNaoExisteException extends NaoEncontradoException {
    public UsuarioNaoExisteException() {
        super("Usuario nao existe.");
    }
}
