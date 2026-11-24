package br.ufal.ic.p2.bikeshare.excecao.formato;

public class NivelBateriaNaoNumericoException extends FormatoInvalidoException {
    public NivelBateriaNaoNumericoException() {
        super("Nivel de bateria deve ser numerico.");
    }
}
