package br.ufal.ic.p2.bikeshare.util;

import br.ufal.ic.p2.bikeshare.excecao.BikeshareException;

import java.math.BigDecimal;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public final class Numeros {
    private static final Pattern INTEIRO = Pattern.compile("-?\\d+");
    private static final Pattern DECIMAL = Pattern.compile("-?\\d+([,.]\\d+)?");

    private Numeros() {
    }

    public static int inteiro(String valor,
                              Supplier<? extends BikeshareException> erroSeVazio,
                              Supplier<? extends BikeshareException> erroSeNaoNumerico) {
        String texto = Texto.obrigatorio(valor, erroSeVazio);
        if (!INTEIRO.matcher(texto).matches()) {
            throw erroSeNaoNumerico.get();
        }
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException grandeDemais) {
            throw erroSeNaoNumerico.get();
        }
    }

    public static BigDecimal decimal(String valor,
                                     Supplier<? extends BikeshareException> erroSeVazio,
                                     Supplier<? extends BikeshareException> erroSeNaoNumerico) {
        String texto = Texto.obrigatorio(valor, erroSeVazio);
        if (!DECIMAL.matcher(texto).matches()) {
            throw erroSeNaoNumerico.get();
        }
        return new BigDecimal(texto.replace(',', '.'));
    }
}
