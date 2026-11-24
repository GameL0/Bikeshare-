package br.ufal.ic.p2.bikeshare.excecao.regra;

public class RemocaoComAluguelEmAndamentoException extends RegraDeNegocioException {
    public RemocaoComAluguelEmAndamentoException() {
        super("Nao e possivel remover usuario com aluguel em andamento.");
    }
}
