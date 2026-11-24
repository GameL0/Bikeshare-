package br.ufal.ic.p2.bikeshare.fabrica;

import br.ufal.ic.p2.bikeshare.modelo.pagamento.MetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;

class FabricaSaldo extends FabricaDeMetodoPagamento {
    @Override
    public MetodoPagamento semDados(Usuario usuario) {
        return usuario.getCarteira();
    }
}
