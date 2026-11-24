package br.ufal.ic.p2.bikeshare.excecao.campo;

public class EnderecoNuloException extends CampoObrigatorioException {
    public EnderecoNuloException() {
        super("Endereco nao pode ser nulo.");
    }
}
