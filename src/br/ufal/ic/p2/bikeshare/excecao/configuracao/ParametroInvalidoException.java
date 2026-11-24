package br.ufal.ic.p2.bikeshare.excecao.configuracao;

public class ParametroInvalidoException extends ConfiguracaoException {
    public ParametroInvalidoException(String chave, String valor, Throwable causa) {
        super("Parametro '" + chave + "' invalido: " + valor + ".", causa);
    }
}
