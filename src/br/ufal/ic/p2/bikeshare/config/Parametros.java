package br.ufal.ic.p2.bikeshare.config;

import br.ufal.ic.p2.bikeshare.excecao.configuracao.ArquivoDeParametrosNaoEncontradoException;
import br.ufal.ic.p2.bikeshare.excecao.configuracao.FalhaAoLerParametrosException;
import br.ufal.ic.p2.bikeshare.excecao.configuracao.ParametroAusenteException;
import br.ufal.ic.p2.bikeshare.excecao.configuracao.ParametroInvalidoException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.function.Function;

public final class Parametros {
    private static final String ARQUIVO = "parametros.properties";

    private final BigDecimal tarifaAvulsoPorMinuto;
    private final BigDecimal adicionalEletricaPorMinuto;
    private final int franquiaAssinanteMinutos;
    private final BigDecimal excedenteAssinantePorMinuto;
    private final BigDecimal mensalidadeAssinante;
    private final int carenciaMinutos;
    private final int tempoMaximoMinutos;
    private final BigDecimal penalidadeAtraso;
    private final int bateriaMinima;

    private Parametros(Properties p) {
        tarifaAvulsoPorMinuto = ler(p, "tarifa.avulso.minuto", BigDecimal::new);
        adicionalEletricaPorMinuto = ler(p, "tarifa.eletrica.adicional.minuto", BigDecimal::new);
        franquiaAssinanteMinutos = ler(p, "assinante.franquia.minutos", Integer::parseInt);
        excedenteAssinantePorMinuto = ler(p, "assinante.excedente.minuto", BigDecimal::new);
        mensalidadeAssinante = ler(p, "assinante.mensalidade", BigDecimal::new);
        carenciaMinutos = ler(p, "aluguel.carencia.minutos", Integer::parseInt);
        tempoMaximoMinutos = ler(p, "aluguel.tempo.maximo.minutos", Integer::parseInt);
        penalidadeAtraso = ler(p, "penalidade.atraso.valor", BigDecimal::new);
        bateriaMinima = ler(p, "eletrica.bateria.minima", Integer::parseInt);
    }

    public static Parametros carregar() {
        Path arquivo = Paths.get(ARQUIVO);
        if (!Files.exists(arquivo)) {
            throw new ArquivoDeParametrosNaoEncontradoException(arquivo.toAbsolutePath().toString());
        }
        Properties propriedades = new Properties();
        try (InputStream entrada = Files.newInputStream(arquivo)) {
            propriedades.load(entrada);
        } catch (IOException e) {
            throw new FalhaAoLerParametrosException(ARQUIVO, e);
        }
        return new Parametros(propriedades);
    }

    private static <T> T ler(Properties p, String chave, Function<String, T> conversor) {
        String valor = p.getProperty(chave);
        if (valor == null || valor.trim().isEmpty()) {
            throw new ParametroAusenteException(chave);
        }
        try {
            return conversor.apply(valor.trim());
        } catch (NumberFormatException e) {
            throw new ParametroInvalidoException(chave, valor, e);
        }
    }

    public BigDecimal getTarifaAvulsoPorMinuto() {
        return tarifaAvulsoPorMinuto;
    }

    public BigDecimal getAdicionalEletricaPorMinuto() {
        return adicionalEletricaPorMinuto;
    }

    public int getFranquiaAssinanteMinutos() {
        return franquiaAssinanteMinutos;
    }

    public BigDecimal getExcedenteAssinantePorMinuto() {
        return excedenteAssinantePorMinuto;
    }

    public BigDecimal getMensalidadeAssinante() {
        return mensalidadeAssinante;
    }

    public int getCarenciaMinutos() {
        return carenciaMinutos;
    }

    public int getTempoMaximoMinutos() {
        return tempoMaximoMinutos;
    }

    public BigDecimal getPenalidadeAtraso() {
        return penalidadeAtraso;
    }

    public int getBateriaMinima() {
        return bateriaMinima;
    }
}
