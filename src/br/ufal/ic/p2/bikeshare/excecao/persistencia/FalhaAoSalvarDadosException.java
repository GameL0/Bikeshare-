package br.ufal.ic.p2.bikeshare.excecao.persistencia;

public class FalhaAoSalvarDadosException extends PersistenciaException {
    public FalhaAoSalvarDadosException(String arquivo, Throwable causa) {
        super("Nao foi possivel salvar os dados em " + arquivo + ".", causa);
    }
}
