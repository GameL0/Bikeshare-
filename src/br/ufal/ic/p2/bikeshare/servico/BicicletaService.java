package br.ufal.ic.p2.bikeshare.servico;

import br.ufal.ic.p2.bikeshare.excecao.campo.IdentificacaoBicicletaNulaException;
import br.ufal.ic.p2.bikeshare.excecao.duplicado.BicicletaJaExisteException;
import br.ufal.ic.p2.bikeshare.excecao.naoencontrado.BicicletaNaoExisteException;
import br.ufal.ic.p2.bikeshare.fabrica.FabricaDeBicicleta;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.sistema.Sistema;
import br.ufal.ic.p2.bikeshare.util.Texto;

import java.util.function.Function;

public class BicicletaService {
    private final Sistema sistema;
    private final EstacaoService estacoes;

    public BicicletaService(Sistema sistema, EstacaoService estacoes) {
        this.sistema = sistema;
        this.estacoes = estacoes;
    }

    public void adicionarConvencional(String idEstacao, String idBicicleta, String tipo) {
        adicionar(idEstacao, idBicicleta, id -> FabricaDeBicicleta.doTipo(tipo).semBateria(id));
    }

    public void adicionarComBateria(String idEstacao, String idBicicleta, String tipo, String nivelBateria) {
        adicionar(idEstacao, idBicicleta, id -> FabricaDeBicicleta.doTipo(tipo).comBateria(id, nivelBateria));
    }

    private void adicionar(String idEstacao, String idBicicleta, Function<String, Bicicleta> criar) {
        Estacao estacao = estacoes.buscar(idEstacao);
        String id = Texto.obrigatorio(idBicicleta, IdentificacaoBicicletaNulaException::new);
        if (sistema.getBicicletas().existe(id)) {
            throw new BicicletaJaExisteException();
        }
        Bicicleta bicicleta = criar.apply(id);
        estacao.validarVagaParaCadastro();
        bicicleta.estacionarEm(estacao);
        sistema.getBicicletas().salvar(bicicleta);
    }

    public void remover(String idEstacao, String idBicicleta) {
        Estacao estacao = estacoes.buscar(idEstacao);
        Bicicleta bicicleta = buscar(idBicicleta);
        bicicleta.validarPresencaEm(estacao);
        bicicleta.validarRemocao();
        bicicleta.remover();
        sistema.getBicicletas().remover(bicicleta);
    }

    public Bicicleta buscar(String id) {
        return sistema.getBicicletas().buscar(id, IdentificacaoBicicletaNulaException::new,
                BicicletaNaoExisteException::new);
    }

    public String getAtributo(String id, String atributo) {
        return buscar(id).getAtributo(atributo);
    }

    public String getEstado(String id) {
        return buscar(id).getEstado();
    }
}
