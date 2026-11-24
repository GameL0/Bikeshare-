package br.ufal.ic.p2.bikeshare.excecao.campo;

public class IdentificacaoEstacaoNulaException extends CampoObrigatorioException {
    public IdentificacaoEstacaoNulaException() {
        super("Identificacao da estacao nao pode ser nula.");
    }
}
