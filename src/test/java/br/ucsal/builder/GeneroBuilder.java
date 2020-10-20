package br.ucsal.builder;

import br.ucsal.model.Genero;

public class GeneroBuilder {
    private Genero genero;

    public GeneroBuilder() {
    }

    public static GeneroBuilder umGenero() {
        GeneroBuilder builder = new GeneroBuilder();
        builder.genero = new Genero();
        builder.genero.setIdGenero(null);
        builder.genero.setNome("Genero Builder");
        return builder;

    }

    public GeneroBuilder comNOme(String nome) {

        genero.setNome(nome);
        return this;
    }

    public Genero agora() {
        return genero;
    }
}
