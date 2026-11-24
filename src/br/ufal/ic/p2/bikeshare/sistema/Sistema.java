package br.ufal.ic.p2.bikeshare.sistema;

import br.ufal.ic.p2.bikeshare.config.Parametros;
import br.ufal.ic.p2.bikeshare.modelo.aluguel.Aluguel;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.modelo.plano.CatalogoDePlanos;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;
import br.ufal.ic.p2.bikeshare.repositorio.GeradorDeId;
import br.ufal.ic.p2.bikeshare.repositorio.Repositorio;

import java.io.Serializable;

public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Repositorio<Usuario> usuarios = new Repositorio<>();
    private final Repositorio<Estacao> estacoes = new Repositorio<>();
    private final Repositorio<Bicicleta> bicicletas = new Repositorio<>();
    private final Repositorio<Aluguel> alugueis = new Repositorio<>();
    private final GeradorDeId idsDeUsuario = new GeradorDeId("U");
    private final GeradorDeId idsDeEstacao = new GeradorDeId("E");
    private final GeradorDeId idsDeAluguel = new GeradorDeId("A");
    private final CatalogoDePlanos planos;

    public Sistema(Parametros parametros) {
        planos = new CatalogoDePlanos(parametros);
    }

    public void aplicar(Parametros parametros) {
        planos.reajustarPadroes(parametros);
    }

    public Repositorio<Usuario> getUsuarios() {
        return usuarios;
    }

    public Repositorio<Estacao> getEstacoes() {
        return estacoes;
    }

    public Repositorio<Bicicleta> getBicicletas() {
        return bicicletas;
    }

    public Repositorio<Aluguel> getAlugueis() {
        return alugueis;
    }

    public GeradorDeId getIdsDeUsuario() {
        return idsDeUsuario;
    }

    public GeradorDeId getIdsDeEstacao() {
        return idsDeEstacao;
    }

    public GeradorDeId getIdsDeAluguel() {
        return idsDeAluguel;
    }

    public CatalogoDePlanos getPlanos() {
        return planos;
    }
}
