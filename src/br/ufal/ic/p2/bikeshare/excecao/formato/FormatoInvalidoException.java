package br.ufal.ic.p2.bikeshare.excecao.formato;

import br.ufal.ic.p2.bikeshare.excecao.BikeshareException;

public abstract class FormatoInvalidoException extends BikeshareException {
    protected FormatoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
