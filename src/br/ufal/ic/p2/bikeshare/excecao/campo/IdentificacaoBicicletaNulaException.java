package br.ufal.ic.p2.bikeshare.excecao.campo;

public class IdentificacaoBicicletaNulaException extends CampoObrigatorioException {
    public IdentificacaoBicicletaNulaException() {
        super("Identificacao da bicicleta nao pode ser nula.");
    }
}
