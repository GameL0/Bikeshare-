package br.ufal.ic.p2.bikeshare.excecao.formato;

public class NivelBateriaForaDoIntervaloException extends FormatoInvalidoException {
    public NivelBateriaForaDoIntervaloException() {
        super("Nivel de bateria deve estar entre 0 e 100.");
    }
}
