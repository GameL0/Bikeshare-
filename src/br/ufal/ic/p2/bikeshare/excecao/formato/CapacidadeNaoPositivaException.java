package br.ufal.ic.p2.bikeshare.excecao.formato;

public class CapacidadeNaoPositivaException extends FormatoInvalidoException {
    public CapacidadeNaoPositivaException() {
        super("Capacidade deve ser positiva.");
    }
}
