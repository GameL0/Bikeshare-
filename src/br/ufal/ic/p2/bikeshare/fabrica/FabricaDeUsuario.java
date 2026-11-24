package br.ufal.ic.p2.bikeshare.fabrica;

import br.ufal.ic.p2.bikeshare.excecao.formato.TipoDeUsuarioInvalidoException;

import java.util.Map;

public abstract class FabricaDeUsuario {
    private static final Map<String, FabricaDeUsuario> POR_TIPO = Map.of(
            "pessoaFisica", new FabricaPessoaFisica(),
            "pessoaJuridica", new FabricaPessoaJuridica());

    public static FabricaDeUsuario doTipo(String tipo) {
        FabricaDeUsuario fabrica = tipo == null ? null : POR_TIPO.get(tipo);
        if (fabrica == null) {
            throw new TipoDeUsuarioInvalidoException();
        }
        return fabrica;
    }

    public MoldeDeUsuario comDocumento(String documento) {
        throw new TipoDeUsuarioInvalidoException();
    }

    public MoldeDeUsuario comRazaoSocial(String cnpj, String razaoSocial) {
        throw new TipoDeUsuarioInvalidoException();
    }
}
