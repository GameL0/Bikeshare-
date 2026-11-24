package br.ufal.ic.p2.bikeshare.modelo.usuario;

import br.ufal.ic.p2.bikeshare.modelo.plano.Assinatura;

public class PessoaJuridica extends Usuario {
    private static final long serialVersionUID = 1L;

    private final String cnpj;
    private final String razaoSocial;

    public PessoaJuridica(String id, String nome, String email, String senha, Assinatura assinatura,
                          String cnpj, String razaoSocial) {
        super(id, nome, email, senha, assinatura);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    @Override
    public String getTipo() {
        return "pessoaJuridica";
    }

    @Override
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getRazaoSocial() {
        return razaoSocial;
    }
}
