package br.ufal.ic.p2.bikeshare.fabrica;

import br.ufal.ic.p2.bikeshare.excecao.campo.CpfNuloException;
import br.ufal.ic.p2.bikeshare.modelo.usuario.PessoaFisica;
import br.ufal.ic.p2.bikeshare.util.Texto;

class FabricaPessoaFisica extends FabricaDeUsuario {
    @Override
    public MoldeDeUsuario comDocumento(String documento) {
        String cpf = Texto.obrigatorio(documento, CpfNuloException::new);
        return (id, dados, assinatura) -> new PessoaFisica(id, dados.getNome(), dados.getEmail(),
                dados.getSenha(), assinatura, cpf);
    }
}
