package br.ufal.ic.p2.bikeshare.excecao.formato;

public class MensalidadeNaoNumericaException extends FormatoInvalidoException {
    public MensalidadeNaoNumericaException() {
        super("Mensalidade deve ser numerica.");
    }
}
