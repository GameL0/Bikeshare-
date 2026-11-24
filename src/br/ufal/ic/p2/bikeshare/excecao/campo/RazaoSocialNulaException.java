package br.ufal.ic.p2.bikeshare.excecao.campo;

public class RazaoSocialNulaException extends CampoObrigatorioException {
    public RazaoSocialNulaException() {
        super("Razao social nao pode ser nula.");
    }
}
