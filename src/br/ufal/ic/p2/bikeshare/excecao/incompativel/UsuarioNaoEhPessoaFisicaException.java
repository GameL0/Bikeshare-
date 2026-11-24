package br.ufal.ic.p2.bikeshare.excecao.incompativel;

public class UsuarioNaoEhPessoaFisicaException extends IncompativelException {
    public UsuarioNaoEhPessoaFisicaException() {
        super("Usuario nao eh pessoa fisica.");
    }
}
