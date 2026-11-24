package br.ufal.ic.p2.bikeshare.excecao.naoencontrado;

public class EstacaoNaoExisteException extends NaoEncontradoException {
    public EstacaoNaoExisteException() {
        super("Estacao nao existe.");
    }
}
