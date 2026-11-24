package br.ufal.ic.p2.bikeshare.modelo.bicicleta;

import br.ufal.ic.p2.bikeshare.config.Parametros;
import br.ufal.ic.p2.bikeshare.excecao.incompativel.BicicletaSemBateriaException;
import br.ufal.ic.p2.bikeshare.excecao.regra.BicicletaForaDaEstacaoException;
import br.ufal.ic.p2.bikeshare.modelo.atributo.LeitorDeAtributos;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.repositorio.Identificavel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class Bicicleta implements Identificavel, Serializable {
    private static final long serialVersionUID = 1L;

    private static final LeitorDeAtributos<Bicicleta> LEITOR = new LeitorDeAtributos<Bicicleta>()
            .com("tipo", Bicicleta::getTipo)
            .com("estado", Bicicleta::getEstado)
            .com("estacao", Bicicleta::getIdEstacao)
            .com("nivelBateria", b -> String.valueOf(b.getNivelBateria()));

    private final String id;
    private EstadoBicicleta estado = EstadoBicicleta.DISPONIVEL;

    private Estacao estacao;

    protected Bicicleta(String id) {
        this.id = Objects.requireNonNull(id, "id");
    }

    public abstract String getTipo();

    public int getNivelBateria() {
        throw new BicicletaSemBateriaException();
    }

    protected boolean temCargaParaSair(Parametros parametros) {
        return true;
    }

    protected void validarCarga(Parametros parametros) {
    }

    public BigDecimal adicionalPorMinuto(Parametros parametros) {
        return BigDecimal.ZERO;
    }

    public String getAtributo(String atributo) {
        return LEITOR.ler(this, atributo);
    }

    @Override
    public String getId() {
        return id;
    }

    public String getEstado() {
        return estado.getNome();
    }

    public String getIdEstacao() {
        return estacao == null ? "nenhuma" : estacao.getId();
    }

    public boolean podeSerRetirada(Parametros parametros) {
        return estado.liberadaParaUso() && temCargaParaSair(parametros);
    }

    public void validarPresencaEm(Estacao local) {
        if (estacao != local) {
            throw new BicicletaForaDaEstacaoException();
        }
    }

    public void validarRetirada(Parametros parametros) {
        estado.validarRetirada();
        validarCarga(parametros);
    }

    public void validarRemocao() {
        estado.validarRemocao();
    }

    public void estacionarEm(Estacao destino) {
        destino.receber(this);
        estacao = destino;
    }

    public void retirar() {
        sairDaEstacao();
        estado = EstadoBicicleta.EM_USO;
    }

    public void devolverEm(Estacao destino) {
        estacionarEm(destino);
        estado = EstadoBicicleta.DISPONIVEL;
    }

    public void devolverParaManutencaoEm(Estacao destino) {
        estacionarEm(destino);
        estado = EstadoBicicleta.EM_MANUTENCAO;
    }

    public void remover() {
        sairDaEstacao();
        estado = EstadoBicicleta.REMOVIDA;
    }

    private void sairDaEstacao() {
        estacao.liberar(this);
        estacao = null;
    }
}
