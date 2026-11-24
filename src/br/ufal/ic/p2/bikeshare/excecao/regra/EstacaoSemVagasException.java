package br.ufal.ic.p2.bikeshare.excecao.regra;

public class EstacaoSemVagasException extends RegraDeNegocioException {
    public EstacaoSemVagasException() {
        super("Estacao sem vagas disponiveis.");
    }
}
