package br.ufal.ic.p2.bikeshare.excecao.formato;

public class MensalidadeNegativaException extends FormatoInvalidoException {
    public MensalidadeNegativaException() {
        super("Mensalidade deve ser nao-negativa.");
    }
}
