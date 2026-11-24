package br.ufal.ic.p2.bikeshare.excecao.naoencontrado;

public class BicicletaNaoExisteException extends NaoEncontradoException {
    public BicicletaNaoExisteException() {
        super("Bicicleta nao existe.");
    }
}
