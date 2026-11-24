package br.ufal.ic.p2.bikeshare.excecao.campo;

public class EmailNuloException extends CampoObrigatorioException {
    public EmailNuloException() {
        super("Email nao pode ser nulo.");
    }
}
