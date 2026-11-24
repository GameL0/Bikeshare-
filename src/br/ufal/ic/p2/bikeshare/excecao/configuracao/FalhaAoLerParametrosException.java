package br.ufal.ic.p2.bikeshare.excecao.configuracao;

public class FalhaAoLerParametrosException extends ConfiguracaoException {
    public FalhaAoLerParametrosException(String arquivo, Throwable causa) {
        super("Nao foi possivel ler " + arquivo + ".", causa);
    }
}
