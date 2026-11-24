package br.ufal.ic.p2.bikeshare.comando;

@FunctionalInterface
public interface Comando<T> {
    T executar();
}
