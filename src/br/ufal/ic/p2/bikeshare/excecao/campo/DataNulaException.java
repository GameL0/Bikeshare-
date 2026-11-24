package br.ufal.ic.p2.bikeshare.excecao.campo;

public class DataNulaException extends CampoObrigatorioException {
    public DataNulaException() {
        super("Data nao pode ser nula.");
    }
}
