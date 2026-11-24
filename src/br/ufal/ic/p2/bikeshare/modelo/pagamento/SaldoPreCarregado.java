package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.formato.ValorNaoPositivoException;
import br.ufal.ic.p2.bikeshare.excecao.regra.SaldoInsuficienteException;

import java.math.BigDecimal;

public class SaldoPreCarregado extends MetodoPagamento {
    private static final long serialVersionUID = 1L;

    private BigDecimal saldo = BigDecimal.ZERO;

    @Override
    public String getNome() {
        return "saldoPreCarregado";
    }

    @Override
    public void autorizarRetirada() {
        if (saldo.signum() <= 0) {
            throw new SaldoInsuficienteException();
        }
    }

    private void validarCobranca(BigDecimal valor) {
        if (valor.compareTo(saldo) > 0) {
            throw new SaldoInsuficienteException();
        }
    }

    @Override
    public void cobrar(BigDecimal valor) {
        validarCobranca(valor);
        saldo = saldo.subtract(valor);
    }

    @Override
    public BigDecimal getSaldo() {
        return saldo;
    }

    @Override
    public void validarRecarga() {
    }

    @Override
    public void recarregar(BigDecimal valor) {
        if (valor.signum() <= 0) {
            throw new ValorNaoPositivoException();
        }
        saldo = saldo.add(valor);
    }
}
