package br.ufal.ic.p2.bikeshare.modelo.plano;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class Plano implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String nome;

    protected Plano(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract BigDecimal calcularTarifa(long minutos);

    public abstract Assinatura contratar(String mensalidadeInformada);

    public abstract Assinatura contratarComValorPadrao();

    abstract BigDecimal mensalidadeDe(Assinatura assinatura);
}
