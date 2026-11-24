package br.ufal.ic.p2.bikeshare.fabrica;

import br.ufal.ic.p2.bikeshare.modelo.pagamento.Cartao;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.MetodoPagamento;

class FabricaCartao extends FabricaDeMetodoPagamento {
    @Override
    public MetodoPagamento comCartao(String numero, String validade) {
        return new Cartao(numero, validade);
    }
}
