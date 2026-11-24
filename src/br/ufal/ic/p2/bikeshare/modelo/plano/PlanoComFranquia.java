package br.ufal.ic.p2.bikeshare.modelo.plano;

import br.ufal.ic.p2.bikeshare.excecao.campo.MensalidadeNulaException;
import br.ufal.ic.p2.bikeshare.excecao.formato.MensalidadeNaoNumericaException;
import br.ufal.ic.p2.bikeshare.excecao.formato.MensalidadeNegativaException;
import br.ufal.ic.p2.bikeshare.util.Numeros;

import java.math.BigDecimal;

public class PlanoComFranquia extends Plano {
    private static final long serialVersionUID = 1L;

    private long franquiaMinutos;
    private BigDecimal valorMinutoExcedente;
    private BigDecimal mensalidadePadrao;

    public PlanoComFranquia(String nome, long franquiaMinutos, BigDecimal valorMinutoExcedente,
                            BigDecimal mensalidadePadrao) {
        super(nome);
        reajustar(franquiaMinutos, valorMinutoExcedente, mensalidadePadrao);
    }

    final void reajustar(long novaFranquia, BigDecimal novoExcedente, BigDecimal novaMensalidadePadrao) {
        this.franquiaMinutos = novaFranquia;
        this.valorMinutoExcedente = novoExcedente;
        this.mensalidadePadrao = novaMensalidadePadrao;
    }

    @Override
    public BigDecimal calcularTarifa(long minutos) {
        long excedente = Math.max(0, minutos - franquiaMinutos);
        return valorMinutoExcedente.multiply(BigDecimal.valueOf(excedente));
    }

    @Override
    public Assinatura contratar(String mensalidadeInformada) {
        BigDecimal mensalidade = Numeros.decimal(mensalidadeInformada,
                MensalidadeNulaException::new, MensalidadeNaoNumericaException::new);
        if (mensalidade.signum() < 0) {
            throw new MensalidadeNegativaException();
        }
        return new Assinatura(this, mensalidade);
    }

    @Override
    public Assinatura contratarComValorPadrao() {
        return new Assinatura(this, mensalidadePadrao);
    }

    @Override
    BigDecimal mensalidadeDe(Assinatura assinatura) {
        return assinatura.getValorContratado();
    }
}
