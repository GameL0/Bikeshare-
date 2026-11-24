package br.ufal.ic.p2.bikeshare.excecao.campo;

public class CpfNuloException extends CampoObrigatorioException {
    public CpfNuloException() {
        super("CPF nao pode ser nulo.");
    }
}
