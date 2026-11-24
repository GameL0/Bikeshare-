package br.ufal.ic.p2.bikeshare.modelo.aluguel;

import br.ufal.ic.p2.bikeshare.config.Parametros;
import br.ufal.ic.p2.bikeshare.excecao.regra.AluguelAindaEmAndamentoException;
import br.ufal.ic.p2.bikeshare.excecao.regra.AluguelNaoEmAndamentoException;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.modelo.penalidade.PenalidadeAtraso;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;

import java.math.BigDecimal;

public enum EstadoAluguel {
    EM_ANDAMENTO("emAndamento") {
        @Override
        void validarEmAndamento() {
        }

        @Override
        void validarEncerrado() {
            throw new AluguelAindaEmAndamentoException();
        }
    },

    FINALIZADO("finalizado") {
        @Override
        BigDecimal calcularValor(Aluguel aluguel, long minutos, Parametros parametros) {
            return aluguel.calcularTarifa(minutos, parametros);
        }
    },

    CANCELADO("cancelado"),

    EXPIRADO("expirado") {
        @Override
        void devolverBicicleta(Bicicleta bicicleta, Estacao destino) {
            bicicleta.devolverParaManutencaoEm(destino);
        }

        @Override
        void aplicarConsequencias(Usuario usuario, Parametros parametros) {
            usuario.adicionarPenalidade(new PenalidadeAtraso(parametros.getPenalidadeAtraso()));
        }
    };

    private final String nome;

    EstadoAluguel(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    static EstadoAluguel classificar(long minutos, boolean mesmaEstacao, Parametros parametros) {
        if (minutos > parametros.getTempoMaximoMinutos()) {
            return EXPIRADO;
        }
        if (mesmaEstacao && minutos <= parametros.getCarenciaMinutos()) {
            return CANCELADO;
        }
        return FINALIZADO;
    }

    void validarEmAndamento() {
        throw new AluguelNaoEmAndamentoException();
    }

    void validarEncerrado() {
    }

    BigDecimal calcularValor(Aluguel aluguel, long minutos, Parametros parametros) {
        return BigDecimal.ZERO;
    }

    void devolverBicicleta(Bicicleta bicicleta, Estacao destino) {
        bicicleta.devolverEm(destino);
    }

    void aplicarConsequencias(Usuario usuario, Parametros parametros) {
    }
}
