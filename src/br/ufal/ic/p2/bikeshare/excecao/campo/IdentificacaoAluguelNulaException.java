package br.ufal.ic.p2.bikeshare.excecao.campo;

public class IdentificacaoAluguelNulaException extends CampoObrigatorioException {
    public IdentificacaoAluguelNulaException() {
        super("Identificacao do aluguel nao pode ser nula.");
    }
}
