package br.ufal.ic.p2.bikeshare.modelo.usuario;

import br.ufal.ic.p2.bikeshare.modelo.plano.Assinatura;

public class PessoaFisica extends Usuario {
    private static final long serialVersionUID = 1L;

    private final String cpf;

    public PessoaFisica(String id, String nome, String email, String senha, Assinatura assinatura, String cpf) {
        super(id, nome, email, senha, assinatura);
        this.cpf = cpf;
    }

    @Override
    public String getTipo() {
        return "pessoaFisica";
    }

    @Override
    public String getCpf() {
        return cpf;
    }
}
