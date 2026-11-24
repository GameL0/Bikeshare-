package br.ufal.ic.p2.bikeshare.excecao.formato;

public class MetodoPagamentoInvalidoException extends FormatoInvalidoException {
    public MetodoPagamentoInvalidoException() {
        super("Valor de metodo de pagamento invalido.");
    }
}
