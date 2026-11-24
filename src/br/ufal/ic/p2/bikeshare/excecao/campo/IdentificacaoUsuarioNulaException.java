package br.ufal.ic.p2.bikeshare.excecao.campo;

public class IdentificacaoUsuarioNulaException extends CampoObrigatorioException {
    public IdentificacaoUsuarioNulaException() {
        super("Identificacao do usuario nao pode ser nula.");
    }
}
