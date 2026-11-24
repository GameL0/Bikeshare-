package br.ufal.ic.p2.bikeshare.modelo.penalidade;

import java.math.BigDecimal;

public class PenalidadeAtraso extends Penalidade {
    private static final long serialVersionUID = 1L;

    public PenalidadeAtraso(BigDecimal valor) {
        super(valor, "Aluguel expirado: tempo maximo de uso excedido");
    }

    @Override
    public String getTipo() {
        return "atraso";
    }
}
