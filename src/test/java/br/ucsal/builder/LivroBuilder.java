package br.ucsal.builder;

import br.ucsal.model.Genero;
import br.ucsal.model.Livro;
import br.ucsal.builder.GeneroBuilder.*;
import br.ucsal.model.Usuario;

import static br.ucsal.builder.GeneroBuilder.umGenero;
import static br.ucsal.builder.UsuarioBuilder.umUsuario;

public class LivroBuilder {

    private Livro livro;


    private LivroBuilder() {
    }

    public static LivroBuilder umLivroDisponivel() {
        Genero genero = umGenero().agora();

        LivroBuilder builder = new LivroBuilder();
        builder.livro = new Livro();
        builder.livro.setIdLivro(null);
        builder.livro.setTitulo("Livro Builder");
        builder.livro.setAutor("Apenas Autor Test");
        builder.livro.setSinopse("Um livro para test builder");
        builder.livro.setDetalhes("Top de Linha");
        builder.livro.setFotoLivro("foto.jpeg");
        builder.livro.setDisponibilidade(true);
        builder.livro.setGenero(genero);
        builder.livro.setUsuario(null);
        return builder;
    }

    public LivroBuilder comTitulo(String nome) {
        livro.setTitulo(nome);
        return this;
    }

    public LivroBuilder comUsuario(Usuario usuairo) {
        livro.setUsuario(usuairo);
        return this;
    }

    public LivroBuilder comGenero(Genero genero) {
        livro.setGenero(genero);
        return this;
    }


    public Livro agora() {
        return livro;
    }


}
