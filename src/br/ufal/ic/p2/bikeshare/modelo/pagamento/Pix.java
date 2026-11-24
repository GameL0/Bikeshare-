package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.campo.ChavePixNulaException;
import br.ufal.ic.p2.bikeshare.util.Texto;

public class Pix extends MetodoPagamento {
    private static final long serialVersionUID = 1L;

    private final String chave;

    public Pix(String chave) {
        this.chave = Texto.obrigatorio(chave, ChavePixNulaException::new);
    }

    @Override
    public String getNome() {
        return "pix";
    }

    @Override
    public void autorizarRetirada() {
    }

    @Override
    public String getChavePix() {
        return chave;
    }
}
