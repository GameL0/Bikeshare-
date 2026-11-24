package br.ufal.ic.p2.bikeshare.fabrica;

import br.ufal.ic.p2.bikeshare.excecao.campo.NivelBateriaNuloException;
import br.ufal.ic.p2.bikeshare.excecao.formato.NivelBateriaNaoNumericoException;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Eletrica;
import br.ufal.ic.p2.bikeshare.util.Numeros;

class FabricaEletrica extends FabricaDeBicicleta {
    @Override
    public Bicicleta semBateria(String id) {
        throw new NivelBateriaNuloException();
    }

    @Override
    public Bicicleta comBateria(String id, String nivelBateria) {
        int nivel = Numeros.inteiro(nivelBateria, NivelBateriaNuloException::new,
                NivelBateriaNaoNumericoException::new);
        return new Eletrica(id, nivel);
    }
}
