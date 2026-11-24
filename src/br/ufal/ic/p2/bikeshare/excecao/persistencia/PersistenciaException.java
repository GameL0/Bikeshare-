package br.ufal.ic.p2.bikeshare.excecao.persistencia;

import br.ufal.ic.p2.bikeshare.excecao.BikeshareException;

public abstract class PersistenciaException extends BikeshareException {
    protected PersistenciaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
