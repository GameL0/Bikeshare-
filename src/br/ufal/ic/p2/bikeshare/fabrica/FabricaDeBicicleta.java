package br.ufal.ic.p2.bikeshare.fabrica;

import br.ufal.ic.p2.bikeshare.excecao.formato.TipoDeBicicletaInvalidoException;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;

import java.util.Map;

public abstract class FabricaDeBicicleta {
    private static final Map<String, FabricaDeBicicleta> POR_TIPO = Map.of(
            "convencional", new FabricaConvencional(),
            "eletrica", new FabricaEletrica());

    public static FabricaDeBicicleta doTipo(String tipo) {
        FabricaDeBicicleta fabrica = tipo == null ? null : POR_TIPO.get(tipo);
        if (fabrica == null) {
            throw new TipoDeBicicletaInvalidoException();
        }
        return fabrica;
    }

    public abstract Bicicleta semBateria(String id);

    public abstract Bicicleta comBateria(String id, String nivelBateria);
}
