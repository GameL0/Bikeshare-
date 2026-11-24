package br.ufal.ic.p2.bikeshare.excecao.naoencontrado;

import br.ufal.ic.p2.bikeshare.excecao.BikeshareException;

public abstract class NaoEncontradoException extends BikeshareException {
    protected NaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
