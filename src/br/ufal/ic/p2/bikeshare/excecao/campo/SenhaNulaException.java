package br.ufal.ic.p2.bikeshare.excecao.campo;

public class SenhaNulaException extends CampoObrigatorioException {
    public SenhaNulaException() {
        super("Senha nao pode ser nula.");
    }
}
