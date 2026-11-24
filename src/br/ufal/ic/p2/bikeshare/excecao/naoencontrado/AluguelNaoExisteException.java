package br.ufal.ic.p2.bikeshare.excecao.naoencontrado;

public class AluguelNaoExisteException extends NaoEncontradoException {
    public AluguelNaoExisteException() {
        super("Aluguel nao existe.");
    }
}
