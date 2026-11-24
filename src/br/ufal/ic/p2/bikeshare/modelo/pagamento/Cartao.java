package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.campo.NumeroCartaoNuloException;
import br.ufal.ic.p2.bikeshare.excecao.campo.ValidadeCartaoNulaException;
import br.ufal.ic.p2.bikeshare.excecao.formato.ValidadeCartaoInvalidaException;
import br.ufal.ic.p2.bikeshare.util.Texto;

import java.util.regex.Pattern;

public class Cartao extends MetodoPagamento {
    private static final long serialVersionUID = 1L;

    private static final Pattern VALIDADE = Pattern.compile("(0?[1-9]|1[0-2])/\\d{4}");

    private final String numero;
    private final String validade;

    public Cartao(String numero, String validade) {
        this.numero = Texto.obrigatorio(numero, NumeroCartaoNuloException::new);
        this.validade = Texto.obrigatorio(validade, ValidadeCartaoNulaException::new);
        if (!VALIDADE.matcher(this.validade).matches()) {
            throw new ValidadeCartaoInvalidaException();
        }
    }

    @Override
    public String getNome() {
        return "cartao";
    }

    @Override
    public void autorizarRetirada() {
    }

    @Override
    public String getNumeroCartao() {
        return numero;
    }

    @Override
    public String getValidadeCartao() {
        return validade;
    }
}
