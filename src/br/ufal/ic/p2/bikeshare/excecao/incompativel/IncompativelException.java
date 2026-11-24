package br.ufal.ic.p2.bikeshare.excecao.incompativel;

import br.ufal.ic.p2.bikeshare.excecao.BikeshareException;

public abstract class IncompativelException extends BikeshareException {
    protected IncompativelException(String mensagem) {
        super(mensagem);
    }
}
