package br.ufal.ic.p2.bikeshare.fabrica;

import br.ufal.ic.p2.bikeshare.excecao.incompativel.BicicletaSemBateriaException;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Convencional;

class FabricaConvencional extends FabricaDeBicicleta {
    @Override
    public Bicicleta semBateria(String id) {
        return new Convencional(id);
    }

    @Override
    public Bicicleta comBateria(String id, String nivelBateria) {
        throw new BicicletaSemBateriaException();
    }
}
