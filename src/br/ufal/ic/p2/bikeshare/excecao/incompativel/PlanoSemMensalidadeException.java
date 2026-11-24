package br.ufal.ic.p2.bikeshare.excecao.incompativel;

public class PlanoSemMensalidadeException extends IncompativelException {
    public PlanoSemMensalidadeException() {
        super("Plano nao possui mensalidade.");
    }
}
