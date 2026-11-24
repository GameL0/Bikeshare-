package br.ufal.ic.p2.bikeshare.excecao.campo;

public class NivelBateriaNuloException extends CampoObrigatorioException {
    public NivelBateriaNuloException() {
        super("Nivel de bateria nao pode ser nulo.");
    }
}
