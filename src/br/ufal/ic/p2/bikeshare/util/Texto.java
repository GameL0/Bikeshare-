package br.ufal.ic.p2.bikeshare.util;

import br.ufal.ic.p2.bikeshare.excecao.BikeshareException;

import java.util.function.Supplier;

public final class Texto {
    private Texto() {
    }

    public static boolean vazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    public static String obrigatorio(String valor, Supplier<? extends BikeshareException> erroSeVazio) {
        if (vazio(valor)) {
            throw erroSeVazio.get();
        }
        return valor.trim();
    }
}
