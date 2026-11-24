package br.ufal.ic.p2.bikeshare.modelo.plano;

import br.ufal.ic.p2.bikeshare.config.Parametros;
import br.ufal.ic.p2.bikeshare.excecao.formato.PlanoInvalidoException;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class CatalogoDePlanos implements Serializable {
    private static final long serialVersionUID = 2L;

    public static final String AVULSO = "avulso";
    public static final String ASSINANTE = "assinante";

    private final Map<String, Plano> planos = new LinkedHashMap<>();

    private final PlanoAvulso avulso;
    private final PlanoComFranquia assinante;

    public CatalogoDePlanos(Parametros parametros) {
        avulso = new PlanoAvulso(AVULSO, parametros.getTarifaAvulsoPorMinuto());
        assinante = new PlanoComFranquia(ASSINANTE, parametros.getFranquiaAssinanteMinutos(),
                parametros.getExcedenteAssinantePorMinuto(), parametros.getMensalidadeAssinante());
        adicionar(avulso);
        adicionar(assinante);
    }

    public void reajustarPadroes(Parametros parametros) {
        avulso.reajustar(parametros.getTarifaAvulsoPorMinuto());
        assinante.reajustar(parametros.getFranquiaAssinanteMinutos(),
                parametros.getExcedenteAssinantePorMinuto(), parametros.getMensalidadeAssinante());
    }

    public final void adicionar(Plano plano) {
        planos.put(plano.getNome(), plano);
    }

    public Plano buscar(String nome) {
        Plano plano = planos.get(nome);
        if (plano == null) {
            throw new PlanoInvalidoException();
        }
        return plano;
    }

    public Plano padrao() {
        return buscar(AVULSO);
    }
}
