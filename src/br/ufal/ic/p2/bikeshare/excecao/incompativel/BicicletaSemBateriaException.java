package br.ufal.ic.p2.bikeshare.excecao.incompativel;

public class BicicletaSemBateriaException extends IncompativelException {
    public BicicletaSemBateriaException() {
        super("Bicicleta nao possui bateria.");
    }
}
