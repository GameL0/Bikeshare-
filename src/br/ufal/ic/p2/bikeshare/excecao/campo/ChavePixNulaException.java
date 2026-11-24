package br.ufal.ic.p2.bikeshare.excecao.campo;

public class ChavePixNulaException extends CampoObrigatorioException {
    public ChavePixNulaException() {
        super("Chave pix nao pode ser nula.");
    }
}
