package br.ufal.ic.p2.bikeshare.excecao.persistencia;

public class FalhaAoCopiarEstadoException extends PersistenciaException {
    public FalhaAoCopiarEstadoException(Throwable causa) {
        super("Nao foi possivel copiar o estado do sistema.", causa);
    }
}
