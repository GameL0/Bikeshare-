package br.ufal.ic.p2.bikeshare.excecao.duplicado;

import br.ufal.ic.p2.bikeshare.excecao.BikeshareException;

public abstract class DuplicadoException extends BikeshareException {
    protected DuplicadoException(String mensagem) {
        super(mensagem);
    }
}
