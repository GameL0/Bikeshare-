package br.ufal.ic.p2.bikeshare.excecao.formato;

public class AtributoNaoAlteravelException extends FormatoInvalidoException {
    public AtributoNaoAlteravelException() {
        super("Atributo nao pode ser alterado.");
    }
}
