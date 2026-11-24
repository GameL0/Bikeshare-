package br.ufal.ic.p2.bikeshare.excecao.campo;

public class CnpjNuloException extends CampoObrigatorioException {
    public CnpjNuloException() {
        super("CNPJ nao pode ser nulo.");
    }
}
