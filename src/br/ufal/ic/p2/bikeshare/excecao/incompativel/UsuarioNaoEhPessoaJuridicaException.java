package br.ufal.ic.p2.bikeshare.excecao.incompativel;

public class UsuarioNaoEhPessoaJuridicaException extends IncompativelException {
    public UsuarioNaoEhPessoaJuridicaException() {
        super("Usuario nao eh pessoa juridica.");
    }
}
