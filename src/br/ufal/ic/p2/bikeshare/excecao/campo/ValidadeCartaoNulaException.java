package br.ufal.ic.p2.bikeshare.excecao.campo;

public class ValidadeCartaoNulaException extends CampoObrigatorioException {
    public ValidadeCartaoNulaException() {
        super("Validade do cartao nao pode ser nula.");
    }
}
