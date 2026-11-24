package br.ufal.ic.p2.bikeshare.excecao.configuracao;

public class ArquivoDeParametrosNaoEncontradoException extends ConfiguracaoException {
    public ArquivoDeParametrosNaoEncontradoException(String arquivo) {
        super("Arquivo " + arquivo + " nao encontrado.");
    }
}
