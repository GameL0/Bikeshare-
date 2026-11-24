package br.ufal.ic.p2.bikeshare.modelo.plano;

import br.ufal.ic.p2.bikeshare.excecao.incompativel.PlanoSemMensalidadeException;
import br.ufal.ic.p2.bikeshare.util.Texto;

import java.math.BigDecimal;

public class PlanoAvulso extends Plano {
    private static final long serialVersionUID = 1L;

    private BigDecimal valorPorMinuto;

    public PlanoAvulso(String nome, BigDecimal valorPorMinuto) {
        super(nome);
        this.valorPorMinuto = valorPorMinuto;
    }

    void reajustar(BigDecimal novoValorPorMinuto) {
        this.valorPorMinuto = novoValorPorMinuto;
    }

    @Override
    public BigDecimal calcularTarifa(long minutos) {
        return valorPorMinuto.multiply(BigDecimal.valueOf(minutos));
    }

    @Override
    public Assinatura contratar(String mensalidadeInformada) {
        if (!Texto.vazio(mensalidadeInformada)) {
            throw new PlanoSemMensalidadeException();
        }
        return contratarComValorPadrao();
    }

    @Override
    public Assinatura contratarComValorPadrao() {
        return new Assinatura(this, BigDecimal.ZERO);
    }

    @Override
    BigDecimal mensalidadeDe(Assinatura assinatura) {
        throw new PlanoSemMensalidadeException();
    }
}
