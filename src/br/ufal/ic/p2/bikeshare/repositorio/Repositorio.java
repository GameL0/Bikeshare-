package br.ufal.ic.p2.bikeshare.repositorio;

import br.ufal.ic.p2.bikeshare.excecao.BikeshareException;
import br.ufal.ic.p2.bikeshare.util.Texto;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Repositorio<T extends Identificavel> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Map<String, T> itens = new LinkedHashMap<>();

    public void salvar(T item) {
        itens.put(item.getId(), item);
    }

    public void remover(T item) {
        itens.remove(item.getId());
    }

    public boolean existe(String id) {
        return itens.containsKey(id);
    }

    public T buscar(String id, Supplier<? extends BikeshareException> erroSeVazio,
                    Supplier<? extends BikeshareException> erroSeNaoExiste) {
        T item = itens.get(Texto.obrigatorio(id, erroSeVazio));
        if (item == null) {
            throw erroSeNaoExiste.get();
        }
        return item;
    }

    public Optional<T> primeiro(Predicate<T> criterio) {
        return itens.values().stream().filter(criterio).findFirst();
    }
}
