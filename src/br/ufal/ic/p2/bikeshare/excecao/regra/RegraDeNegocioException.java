package br.ufal.ic.p2.bikeshare.excecao.regra;

import br.ufal.ic.p2.bikeshare.excecao.BikeshareException;

public abstract class RegraDeNegocioException extends BikeshareException {
    protected RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }
}
