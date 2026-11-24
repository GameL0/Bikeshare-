package br.ufal.ic.p2.bikeshare.excecao.formato;

public class AtributoNaoExisteException extends FormatoInvalidoException {
    public AtributoNaoExisteException() {
        super("Atributo nao existe.");
    }
}
