package br.ufal.ic.p2.bikeshare.repositorio;

import java.io.Serializable;

public class GeradorDeId implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String prefixo;
    private int ultimo;

    public GeradorDeId(String prefixo) {
        this.prefixo = prefixo;
    }

    public String proximo() {
        ultimo++;
        return prefixo + ultimo;
    }
}
