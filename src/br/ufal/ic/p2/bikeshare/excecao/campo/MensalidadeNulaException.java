package br.ufal.ic.p2.bikeshare.excecao.campo;

public class MensalidadeNulaException extends CampoObrigatorioException {
    public MensalidadeNulaException() {
        super("Mensalidade nao pode ser nula.");
    }
}
