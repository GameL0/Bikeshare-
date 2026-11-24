package br.ufal.ic.p2.bikeshare.excecao.configuracao;

public class ParametroAusenteException extends ConfiguracaoException {
    public ParametroAusenteException(String chave) {
        super("Parametro '" + chave + "' ausente.");
    }
}
