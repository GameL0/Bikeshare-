package br.ufal.ic.p2.bikeshare.excecao.campo;

public class NomeNuloException extends CampoObrigatorioException {
    public NomeNuloException() {
        super("Nome nao pode ser nulo.");
    }
}
