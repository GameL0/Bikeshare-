package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.incompativel.UsuarioNaoUsaCartaoException;
import br.ufal.ic.p2.bikeshare.excecao.incompativel.UsuarioNaoUsaPixException;
import br.ufal.ic.p2.bikeshare.excecao.incompativel.UsuarioNaoUsaSaldoException;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class MetodoPagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    public abstract String getNome();

    public abstract void autorizarRetirada();

    public void cobrar(BigDecimal valor) {
    }

    public String getNumeroCartao() {
        throw new UsuarioNaoUsaCartaoException();
    }

    public String getValidadeCartao() {
        throw new UsuarioNaoUsaCartaoException();
    }

    public String getChavePix() {
        throw new UsuarioNaoUsaPixException();
    }

    public BigDecimal getSaldo() {
        throw new UsuarioNaoUsaSaldoException();
    }

    public void validarRecarga() {
        throw new UsuarioNaoUsaSaldoException();
    }

    public void recarregar(BigDecimal valor) {
        throw new UsuarioNaoUsaSaldoException();
    }
}
