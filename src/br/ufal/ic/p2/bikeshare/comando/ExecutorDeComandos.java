package br.ufal.ic.p2.bikeshare.comando;

import br.ufal.ic.p2.bikeshare.sistema.CopiaDoSistema;
import br.ufal.ic.p2.bikeshare.sistema.Sistema;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExecutorDeComandos {
    private final Supplier<Sistema> sistemaAtual;
    private final Consumer<Sistema> restaurar;

    public ExecutorDeComandos(Supplier<Sistema> sistemaAtual, Consumer<Sistema> restaurar) {
        this.sistemaAtual = sistemaAtual;
        this.restaurar = restaurar;
    }

    public <T> T executar(Comando<T> comando) {
        CopiaDoSistema antes = CopiaDoSistema.de(sistemaAtual.get());
        try {
            return comando.executar();
        } catch (RuntimeException e) {
            restaurar.accept(antes.restaurar());
            throw e;
        }
    }

    public void executar(Runnable acao) {
        executar(() -> {
            acao.run();
            return null;
        });
    }
}
