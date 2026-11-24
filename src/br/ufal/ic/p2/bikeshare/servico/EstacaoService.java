package br.ufal.ic.p2.bikeshare.servico;

import br.ufal.ic.p2.bikeshare.config.Parametros;
import br.ufal.ic.p2.bikeshare.excecao.campo.CapacidadeNulaException;
import br.ufal.ic.p2.bikeshare.excecao.campo.EnderecoNuloException;
import br.ufal.ic.p2.bikeshare.excecao.campo.IdentificacaoEstacaoNulaException;
import br.ufal.ic.p2.bikeshare.excecao.campo.NomeNuloException;
import br.ufal.ic.p2.bikeshare.excecao.formato.CapacidadeNaoNumericaException;
import br.ufal.ic.p2.bikeshare.excecao.naoencontrado.EstacaoNaoExisteException;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.sistema.Sistema;
import br.ufal.ic.p2.bikeshare.util.Numeros;
import br.ufal.ic.p2.bikeshare.util.Texto;

public class EstacaoService {
    private final Sistema sistema;
    private final Parametros parametros;

    public EstacaoService(Sistema sistema, Parametros parametros) {
        this.sistema = sistema;
        this.parametros = parametros;
    }

    public String criar(String nome, String endereco, String capacidadeInformada) {
        String nomeValido = Texto.obrigatorio(nome, NomeNuloException::new);
        String enderecoValido = Texto.obrigatorio(endereco, EnderecoNuloException::new);
        int capacidade = Numeros.inteiro(capacidadeInformada, CapacidadeNulaException::new,
                CapacidadeNaoNumericaException::new);
        Estacao estacao = new Estacao(sistema.getIdsDeEstacao().proximo(), nomeValido, enderecoValido, capacidade);
        sistema.getEstacoes().salvar(estacao);
        return estacao.getId();
    }

    public Estacao buscar(String id) {
        return sistema.getEstacoes().buscar(id, IdentificacaoEstacaoNulaException::new,
                EstacaoNaoExisteException::new);
    }

    public String getVagasDisponiveis(String id) {
        return String.valueOf(buscar(id).getVagasDisponiveis());
    }

    public String getBicicletasDisponiveis(String id) {
        return String.valueOf(buscar(id).getBicicletasDisponiveis(parametros));
    }
}
