package br.ufal.ic.p2.bikeshare.excecao.regra;

public class SaldoInsuficienteException extends RegraDeNegocioException {
    public SaldoInsuficienteException() {
        super("Saldo insuficiente.");
    }
}
