package br.ufal.ic.p2.bikeshare.sistema;

import br.ufal.ic.p2.bikeshare.excecao.persistencia.FalhaAoCopiarEstadoException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class CopiaDoSistema {
    private final byte[] estado;

    private CopiaDoSistema(byte[] estado) {
        this.estado = estado;
    }

    public static CopiaDoSistema de(Sistema sistema) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream saida = new ObjectOutputStream(bytes)) {
            saida.writeObject(sistema);
        } catch (IOException e) {
            throw new FalhaAoCopiarEstadoException(e);
        }
        return new CopiaDoSistema(bytes.toByteArray());
    }

    public Sistema restaurar() {
        try (ObjectInputStream entrada = new ObjectInputStream(new ByteArrayInputStream(estado))) {
            return (Sistema) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new FalhaAoCopiarEstadoException(e);
        }
    }
}
