package br.ufal.ic.p2.bikeshare.excecao.regra;

public class UsuarioInadimplenteException extends RegraDeNegocioException {
    public UsuarioInadimplenteException() {
        super("Usuario inadimplente.");
    }
}
