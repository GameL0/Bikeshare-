package br.ufal.ic.p2.bikeshare.modelo.aluguel;

import br.ufal.ic.p2.bikeshare.config.Parametros;
import br.ufal.ic.p2.bikeshare.excecao.regra.DevolucaoAntesDaRetiradaException;
import br.ufal.ic.p2.bikeshare.modelo.atributo.LeitorDeAtributos;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;
import br.ufal.ic.p2.bikeshare.repositorio.Identificavel;
import br.ufal.ic.p2.bikeshare.util.Datas;
import br.ufal.ic.p2.bikeshare.util.Dinheiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Supplier;

public class Aluguel implements Identificavel, Serializable {
    private static final long serialVersionUID = 1L;

    private static final LeitorDeAtributos<Aluguel> LEITOR = new LeitorDeAtributos<Aluguel>()
            .com("estado", a -> a.estado.getNome())
            .com("usuario", a -> a.usuario.getId())
            .com("bicicleta", a -> a.bicicleta.getId())
            .com("estacaoOrigem", a -> a.origem.getId())
            .com("inicio", a -> Datas.formatar(a.inicio))
            .com("fim", a -> a.encerrado(() -> Datas.formatar(a.fim)))
            .com("estacaoDestino", a -> a.encerrado(() -> a.destino.getId()))
            .com("valorCobrado", a -> a.encerrado(() -> Dinheiro.formatar(a.valorCobrado)));

    private final String id;
    private final Usuario usuario;
    private final Bicicleta bicicleta;
    private final Estacao origem;
    private final LocalDateTime inicio;
    private EstadoAluguel estado = EstadoAluguel.EM_ANDAMENTO;
    private Estacao destino;
    private LocalDateTime fim;
    private BigDecimal valorCobrado = BigDecimal.ZERO;

    public Aluguel(String id, Usuario usuario, Bicicleta bicicleta, Estacao origem, LocalDateTime inicio) {
        this.id = Objects.requireNonNull(id, "id");
        this.usuario = Objects.requireNonNull(usuario, "usuario");
        this.bicicleta = Objects.requireNonNull(bicicleta, "bicicleta");
        this.origem = Objects.requireNonNull(origem, "origem");
        this.inicio = Objects.requireNonNull(inicio, "inicio");
        bicicleta.validarPresencaEm(origem);
        usuario.iniciarAluguel(this);
        bicicleta.retirar();
    }

    public void devolver(Estacao estacaoDestino, LocalDateTime dataFim, Parametros parametros) {
        estado.validarEmAndamento();
        if (dataFim.isBefore(inicio)) {
            throw new DevolucaoAntesDaRetiradaException();
        }
        estacaoDestino.validarVagaParaDevolucao();

        long minutos = Duration.between(inicio, dataFim).toMinutes();
        EstadoAluguel estadoFinal = EstadoAluguel.classificar(minutos, estacaoDestino == origem, parametros);
        BigDecimal valor = estadoFinal.calcularValor(this, minutos, parametros);
        usuario.cobrar(valor);
        estadoFinal.devolverBicicleta(bicicleta, estacaoDestino);
        estadoFinal.aplicarConsequencias(usuario, parametros);
        usuario.encerrarAluguel();
        estado = estadoFinal;
        destino = estacaoDestino;
        fim = dataFim;
        valorCobrado = valor;
    }

    BigDecimal calcularTarifa(long minutos, Parametros parametros) {
        BigDecimal tarifaDoPlano = usuario.calcularTarifa(minutos);
        BigDecimal adicional = bicicleta.adicionalPorMinuto(parametros).multiply(BigDecimal.valueOf(minutos));
        return tarifaDoPlano.add(adicional);
    }

    public String getAtributo(String atributo) {
        return LEITOR.ler(this, atributo);
    }

    private String encerrado(Supplier<String> valor) {
        estado.validarEncerrado();
        return valor.get();
    }

    @Override
    public String getId() {
        return id;
    }
}
