package br.ufal.ic.p2.bikeshare.excecao.campo;

import br.ufal.ic.p2.bikeshare.excecao.BikeshareException;

public abstract class CampoObrigatorioException extends BikeshareException {
    protected CampoObrigatorioException(String mensagem) {
        super(mensagem);
    }
}
