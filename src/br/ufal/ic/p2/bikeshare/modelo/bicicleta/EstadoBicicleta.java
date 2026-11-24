package br.ufal.ic.p2.bikeshare.modelo.bicicleta;

import br.ufal.ic.p2.bikeshare.excecao.regra.BicicletaIndisponivelException;

public enum EstadoBicicleta {
    DISPONIVEL("disponivel") {
        @Override
        void validarRetirada() {
        }

        @Override
        void validarRemocao() {
        }

        @Override
        boolean liberadaParaUso() {
            return true;
        }
    },

    EM_USO("emUso"),

    EM_MANUTENCAO("emManutencao"),

    REMOVIDA("removida");

    private final String nome;

    EstadoBicicleta(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    void validarRetirada() {
        throw new BicicletaIndisponivelException();
    }

    void validarRemocao() {
        throw new BicicletaIndisponivelException();
    }

    boolean liberadaParaUso() {
        return false;
    }
}
