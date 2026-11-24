package br.ufal.ic.p2.bikeshare.sistema;

import br.ufal.ic.p2.bikeshare.excecao.persistencia.FalhaAoCarregarDadosException;
import br.ufal.ic.p2.bikeshare.excecao.persistencia.FalhaAoSalvarDadosException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class Persistencia {
    private final Path arquivo;

    public Persistencia() {
        this(Paths.get("bikeshare_data.ser"));
    }

    public Persistencia(Path arquivo) {
        this.arquivo = arquivo;
    }

    public void salvar(Sistema sistema) {
        try (ObjectOutputStream saida = new ObjectOutputStream(Files.newOutputStream(arquivo))) {
            saida.writeObject(sistema);
        } catch (IOException e) {
            throw new FalhaAoSalvarDadosException(arquivo.toString(), e);
        }
    }

    public Optional<Sistema> carregar() {
        if (!Files.exists(arquivo)) {
            return Optional.empty();
        }
        try (ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(arquivo))) {
            return Optional.of((Sistema) entrada.readObject());
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            throw new FalhaAoCarregarDadosException(arquivo.toString(), e);
        }
    }
}
