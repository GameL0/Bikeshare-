package br.ufal.ic.p2.bikeshare.excecao.duplicado;

public class BicicletaJaExisteException extends DuplicadoException {
    public BicicletaJaExisteException() {
        super("Bicicleta ja existe.");
    }
}
