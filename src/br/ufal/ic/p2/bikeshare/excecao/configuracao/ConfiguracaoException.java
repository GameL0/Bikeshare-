package br.ufal.ic.p2.bikeshare.excecao.configuracao;

import br.ufal.ic.p2.bikeshare.excecao.BikeshareException;

public abstract class ConfiguracaoException extends BikeshareException {
    protected ConfiguracaoException(String mensagem) {
        super(mensagem);
    }

    protected ConfiguracaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
