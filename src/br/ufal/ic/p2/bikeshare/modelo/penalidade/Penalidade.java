package br.ufal.ic.p2.bikeshare.modelo.penalidade;

import br.ufal.ic.p2.bikeshare.excecao.formato.ValorNaoPositivoException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class Penalidade implements Serializable {
    private static final long serialVersionUID = 1L;

    private final BigDecimal valor;
    private final String motivo;

    protected Penalidade(BigDecimal valor, String motivo) {
        if (valor.signum() <= 0) {
            throw new ValorNaoPositivoException();
        }
        this.valor = valor;
        this.motivo = Objects.requireNonNull(motivo, "motivo");
    }

    public abstract String getTipo();

    public BigDecimal getValor() {
        return valor;
    }

    public String getMotivo() {
        return motivo;
    }
}
