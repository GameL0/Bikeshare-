package br.ufal.ic.p2.bikeshare.fabrica;

import br.ufal.ic.p2.bikeshare.modelo.pagamento.MetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.Pix;

class FabricaPix extends FabricaDeMetodoPagamento {
    @Override
    public MetodoPagamento comChave(String chave) {
        return new Pix(chave);
    }
}
