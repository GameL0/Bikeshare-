package br.ufal.ic.p2.bikeshare.modelo.atributo;

import br.ufal.ic.p2.bikeshare.excecao.formato.AtributoNaoExisteException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class LeitorDeAtributos<T> {
    private final Map<String, Function<T, String>> leitores = new HashMap<>();

    public LeitorDeAtributos<T> com(String atributo, Function<T, String> leitor) {
        leitores.put(atributo, leitor);
        return this;
    }

    public String ler(T alvo, String atributo) {
        Function<T, String> leitor = leitores.get(atributo);
        if (leitor == null) {
            throw new AtributoNaoExisteException();
        }
        return leitor.apply(alvo);
    }
}
