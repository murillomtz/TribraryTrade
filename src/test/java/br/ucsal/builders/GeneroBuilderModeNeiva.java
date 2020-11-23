package br.ucsal.builders;

import br.ucsal.model.Genero;

public class GeneroBuilderModeNeiva {


    private static final Integer ID_DEFAULT = null;
    private static final String NOME_DEFAULT = "Genero Builder";

    private Integer id = ID_DEFAULT;
    private String nome = NOME_DEFAULT;


    public GeneroBuilderModeNeiva() {
    }

    public static GeneroBuilderModeNeiva umGenero() {
        return new GeneroBuilderModeNeiva();
    }

    public GeneroBuilderModeNeiva comId(Integer id) {
        this.id = id;
        return this;
    }

    public GeneroBuilderModeNeiva comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public GeneroBuilderModeNeiva mas() {
        return new GeneroBuilderModeNeiva().comId(id).comNome(nome);
    }

    public Genero build() {
        Genero genero = new Genero();
        genero.setIdGenero(id);
        genero.setNome(nome);

        return genero;
    }


}
