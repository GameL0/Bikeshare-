package br.ufal.ic.p2.bikeshare.modelo.plano;

import java.io.Serializable;
import java.math.BigDecimal;

public final class Assinatura implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Plano plano;
    private final BigDecimal valorContratado;

    Assinatura(Plano plano, BigDecimal valorContratado) {
        this.plano = plano;
        this.valorContratado = valorContratado;
    }

    public Plano getPlano() {
        return plano;
    }

    public boolean ehDoMesmoPlanoQue(Assinatura outra) {
        return plano == outra.plano;
    }

    public BigDecimal getMensalidade() {
        return plano.mensalidadeDe(this);
    }

    BigDecimal getValorContratado() {
        return valorContratado;
    }
}
