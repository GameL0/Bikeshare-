package br.ufal.ic.p2.bikeshare.modelo.estacao;

import br.ufal.ic.p2.bikeshare.config.Parametros;
import br.ufal.ic.p2.bikeshare.excecao.campo.EnderecoNuloException;
import br.ufal.ic.p2.bikeshare.excecao.campo.NomeNuloException;
import br.ufal.ic.p2.bikeshare.excecao.formato.CapacidadeNaoPositivaException;
import br.ufal.ic.p2.bikeshare.excecao.regra.EstacaoCheiaException;
import br.ufal.ic.p2.bikeshare.excecao.regra.EstacaoSemVagasException;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.repositorio.Identificavel;
import br.ufal.ic.p2.bikeshare.util.Texto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Estacao implements Identificavel, Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private final String nome;
    private final String endereco;
    private final int capacidade;
    private final List<Bicicleta> bicicletas = new ArrayList<>();

    public Estacao(String id, String nome, String endereco, int capacidade) {
        if (capacidade <= 0) {
            throw new CapacidadeNaoPositivaException();
        }
        this.id = Objects.requireNonNull(id, "id");
        this.nome = Texto.obrigatorio(nome, NomeNuloException::new);
        this.endereco = Texto.obrigatorio(endereco, EnderecoNuloException::new);
        this.capacidade = capacidade;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getVagasDisponiveis() {
        return capacidade - bicicletas.size();
    }

    public long getBicicletasDisponiveis(Parametros parametros) {
        return bicicletas.stream().filter(b -> b.podeSerRetirada(parametros)).count();
    }

    public void validarVagaParaCadastro() {
        if (getVagasDisponiveis() <= 0) {
            throw new EstacaoSemVagasException();
        }
    }

    public void validarVagaParaDevolucao() {
        if (getVagasDisponiveis() <= 0) {
            throw new EstacaoCheiaException();
        }
    }

    public void receber(Bicicleta bicicleta) {
        validarVagaParaDevolucao();
        bicicletas.add(Objects.requireNonNull(bicicleta, "bicicleta"));
    }

    public void liberar(Bicicleta bicicleta) {
        bicicletas.remove(bicicleta);
    }
}
