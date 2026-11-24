package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.regra.MetodoPagamentoNaoCadastradoException;

public class SemMetodoPagamento extends MetodoPagamento {
    private static final long serialVersionUID = 1L;

    @Override
    public String getNome() {
        return "nenhum";
    }

    @Override
    public void autorizarRetirada() {
        throw new MetodoPagamentoNaoCadastradoException();
    }
}
