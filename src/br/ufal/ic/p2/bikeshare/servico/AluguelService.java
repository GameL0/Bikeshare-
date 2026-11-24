package br.ufal.ic.p2.bikeshare.servico;

import br.ufal.ic.p2.bikeshare.config.Parametros;
import br.ufal.ic.p2.bikeshare.excecao.campo.IdentificacaoAluguelNulaException;
import br.ufal.ic.p2.bikeshare.excecao.naoencontrado.AluguelNaoExisteException;
import br.ufal.ic.p2.bikeshare.modelo.aluguel.Aluguel;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;
import br.ufal.ic.p2.bikeshare.sistema.Sistema;
import br.ufal.ic.p2.bikeshare.util.Datas;

import java.time.LocalDateTime;

public class AluguelService {
    private final Sistema sistema;
    private final Parametros parametros;
    private final UsuarioService usuarios;
    private final EstacaoService estacoes;
    private final BicicletaService bicicletas;

    public AluguelService(Sistema sistema, Parametros parametros, UsuarioService usuarios,
                          EstacaoService estacoes, BicicletaService bicicletas) {
        this.sistema = sistema;
        this.parametros = parametros;
        this.usuarios = usuarios;
        this.estacoes = estacoes;
        this.bicicletas = bicicletas;
    }

    public String retirar(String idUsuario, String idEstacao, String idBicicleta, String data) {
        Usuario usuario = usuarios.buscar(idUsuario);
        Estacao estacao = estacoes.buscar(idEstacao);
        Bicicleta bicicleta = bicicletas.buscar(idBicicleta);
        LocalDateTime inicio = Datas.lerDataHora(data);

        usuario.validarNovaRetirada();
        bicicleta.validarPresencaEm(estacao);
        bicicleta.validarRetirada(parametros);
        usuario.autorizarRetirada();

        Aluguel aluguel = new Aluguel(sistema.getIdsDeAluguel().proximo(), usuario, bicicleta, estacao, inicio);
        sistema.getAlugueis().salvar(aluguel);
        return aluguel.getId();
    }

    public void devolver(String idAluguel, String idEstacao, String data) {
        Aluguel aluguel = buscar(idAluguel);
        Estacao destino = estacoes.buscar(idEstacao);
        LocalDateTime fim = Datas.lerDataHora(data);
        aluguel.devolver(destino, fim, parametros);
    }

    public Aluguel buscar(String id) {
        return sistema.getAlugueis().buscar(id, IdentificacaoAluguelNulaException::new,
                AluguelNaoExisteException::new);
    }

    public String getAtributo(String id, String atributo) {
        return buscar(id).getAtributo(atributo);
    }
}
