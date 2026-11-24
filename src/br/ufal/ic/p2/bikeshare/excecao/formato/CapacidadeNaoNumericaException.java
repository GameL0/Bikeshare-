package br.ufal.ic.p2.bikeshare.excecao.formato;

public class CapacidadeNaoNumericaException extends FormatoInvalidoException {
    public CapacidadeNaoNumericaException() {
        super("Capacidade deve ser numerica.");
    }
}
