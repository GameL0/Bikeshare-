package br.ufal.ic.p2.bikeshare.excecao.regra;

public class BateriaInsuficienteException extends RegraDeNegocioException {
    public BateriaInsuficienteException() {
        super("Nivel de bateria insuficiente.");
    }
}
