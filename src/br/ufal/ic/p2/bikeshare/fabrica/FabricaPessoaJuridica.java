package br.ufal.ic.p2.bikeshare.fabrica;

import br.ufal.ic.p2.bikeshare.excecao.campo.CnpjNuloException;
import br.ufal.ic.p2.bikeshare.excecao.campo.RazaoSocialNulaException;
import br.ufal.ic.p2.bikeshare.modelo.usuario.PessoaJuridica;
import br.ufal.ic.p2.bikeshare.util.Texto;

class FabricaPessoaJuridica extends FabricaDeUsuario {
    @Override
    public MoldeDeUsuario comRazaoSocial(String cnpjInformado, String razaoInformada) {
        String cnpj = Texto.obrigatorio(cnpjInformado, CnpjNuloException::new);
        String razaoSocial = Texto.obrigatorio(razaoInformada, RazaoSocialNulaException::new);
        return (id, dados, assinatura) -> new PessoaJuridica(id, dados.getNome(), dados.getEmail(),
                dados.getSenha(), assinatura, cnpj, razaoSocial);
    }
}
