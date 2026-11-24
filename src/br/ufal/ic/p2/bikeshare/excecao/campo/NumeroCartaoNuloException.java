package br.ufal.ic.p2.bikeshare.excecao.campo;

public class NumeroCartaoNuloException extends CampoObrigatorioException {
    public NumeroCartaoNuloException() {
        super("Numero do cartao nao pode ser nulo.");
    }
}
