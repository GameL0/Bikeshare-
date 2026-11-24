package br.ufal.ic.p2.bikeshare.fabrica;

import br.ufal.ic.p2.bikeshare.modelo.plano.Assinatura;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;

@FunctionalInterface
public interface MoldeDeUsuario {
    Usuario construir(String id, DadosDeCadastro dados, Assinatura assinatura);
}
