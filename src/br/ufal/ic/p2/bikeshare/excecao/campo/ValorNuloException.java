package br.ufal.ic.p2.bikeshare.excecao.campo;

public class ValorNuloException extends CampoObrigatorioException {
    public ValorNuloException() {
        super("Valor nao pode ser nulo.");
    }
}
