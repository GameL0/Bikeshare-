package br.ufal.ic.p2.bikeshare.excecao.persistencia;

public class FalhaAoCarregarDadosException extends PersistenciaException {
    public FalhaAoCarregarDadosException(String arquivo, Throwable causa) {
        super("Nao foi possivel carregar os dados de " + arquivo + ".", causa);
    }
}
