package br.ufal.ic.p2.bikeshare.modelo.bicicleta;

import br.ufal.ic.p2.bikeshare.config.Parametros;
import br.ufal.ic.p2.bikeshare.excecao.formato.NivelBateriaForaDoIntervaloException;
import br.ufal.ic.p2.bikeshare.excecao.regra.BateriaInsuficienteException;

import java.math.BigDecimal;

public class Eletrica extends Bicicleta {
    private static final long serialVersionUID = 1L;

    private final int nivelBateria;

    public Eletrica(String id, int nivelBateria) {
        super(id);
        if (nivelBateria < 0 || nivelBateria > 100) {
            throw new NivelBateriaForaDoIntervaloException();
        }
        this.nivelBateria = nivelBateria;
    }

    @Override
    public String getTipo() {
        return "eletrica";
    }

    @Override
    public int getNivelBateria() {
        return nivelBateria;
    }

    @Override
    protected boolean temCargaParaSair(Parametros parametros) {
        return nivelBateria >= parametros.getBateriaMinima();
    }

    @Override
    protected void validarCarga(Parametros parametros) {
        if (!temCargaParaSair(parametros)) {
            throw new BateriaInsuficienteException();
        }
    }

    @Override
    public BigDecimal adicionalPorMinuto(Parametros parametros) {
        return parametros.getAdicionalEletricaPorMinuto();
    }
}
