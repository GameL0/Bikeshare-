package br.ufal.ic.p2.bikeshare.util;

import br.ufal.ic.p2.bikeshare.excecao.campo.DataNulaException;
import br.ufal.ic.p2.bikeshare.excecao.formato.DataInvalidaException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public final class Datas {
    private static final DateTimeFormatter DATA_HORA =
            DateTimeFormatter.ofPattern("d/M/uuuu H:mm").withResolverStyle(ResolverStyle.STRICT);

    private Datas() {
    }

    public static LocalDateTime lerDataHora(String valor) {
        String texto = Texto.obrigatorio(valor, DataNulaException::new);
        try {
            return LocalDateTime.parse(texto, DATA_HORA);
        } catch (DateTimeParseException e) {
            throw new DataInvalidaException();
        }
    }

    public static String formatar(LocalDateTime dataHora) {
        return dataHora.format(DATA_HORA);
    }
}
