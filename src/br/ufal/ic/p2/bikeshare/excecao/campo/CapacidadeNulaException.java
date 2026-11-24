package br.ufal.ic.p2.bikeshare.excecao.campo;

public class CapacidadeNulaException extends CampoObrigatorioException {
    public CapacidadeNulaException() {
        super("Capacidade nao pode ser nula.");
    }
}
