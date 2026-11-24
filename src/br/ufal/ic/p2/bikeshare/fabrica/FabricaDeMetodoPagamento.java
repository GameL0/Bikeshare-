package br.ufal.ic.p2.bikeshare.fabrica;

import br.ufal.ic.p2.bikeshare.excecao.formato.MetodoPagamentoInvalidoException;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.MetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;

import java.util.Map;

public abstract class FabricaDeMetodoPagamento {
    private static final Map<String, FabricaDeMetodoPagamento> POR_NOME = Map.of(
            "cartao", new FabricaCartao(),
            "pix", new FabricaPix(),
            "saldoPreCarregado", new FabricaSaldo());

    public static FabricaDeMetodoPagamento doNome(String nome) {
        FabricaDeMetodoPagamento fabrica = nome == null ? null : POR_NOME.get(nome);
        if (fabrica == null) {
            throw new MetodoPagamentoInvalidoException();
        }
        return fabrica;
    }

    public MetodoPagamento semDados(Usuario usuario) {
        throw new MetodoPagamentoInvalidoException();
    }

    public MetodoPagamento comChave(String chave) {
        throw new MetodoPagamentoInvalidoException();
    }

    public MetodoPagamento comCartao(String numero, String validade) {
        throw new MetodoPagamentoInvalidoException();
    }
}
