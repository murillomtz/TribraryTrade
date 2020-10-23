package br.ucsal.builder;

import br.ucsal.model.Endereco;
import br.ucsal.model.Genero;
import br.ucsal.model.Livro;
import br.ucsal.model.Usuario;

public class LivroBuilderModeNeiva {


    private static final Integer ID_DEFAULT = null;
    private static final String TITULO_DEFAULT = "Livro Builder";
    private static final String AUTOR_DEFAULT = "Apenas Autor Test";
    private static final String SINOPSE_DEFAULT = "Um livro para test builder";
    private static final String DETALHE_DEFAULT = "Top de Linha";
    private static final String FOTO_DEFAULT = "foto.jpeg";
    private static final Boolean DISPONIBILIDADE_DEFAULT = Boolean.TRUE;
    private static final Genero GENERO_DEFAULT = null;
    private static final Usuario USUARIO_DEFAULT = null;

    private Integer id = ID_DEFAULT;
    private String titulo = TITULO_DEFAULT;
    private String autor = AUTOR_DEFAULT;
    private String sinopse = SINOPSE_DEFAULT;
    private String detalhes = DETALHE_DEFAULT;
    private String fotoLivro = FOTO_DEFAULT;
    private Boolean disponibilidade = DISPONIBILIDADE_DEFAULT;
    private Genero genero = GENERO_DEFAULT;
    private Usuario usuario = USUARIO_DEFAULT;


    public LivroBuilderModeNeiva() {
    }

    public static LivroBuilderModeNeiva umLivro() {
        return new LivroBuilderModeNeiva();
    }

    public LivroBuilderModeNeiva comId(Integer id) {
        this.id = id;
        return this;
    }

    public LivroBuilderModeNeiva comTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public LivroBuilderModeNeiva comAutor(String autor) {
        this.autor = autor;
        return this;
    }

    public LivroBuilderModeNeiva comSinopse(String sinopse) {
        this.sinopse = sinopse;
        return this;
    }

    public LivroBuilderModeNeiva comDetalhes(String detalhes) {
        this.detalhes = detalhes;
        return this;
    }

    public LivroBuilderModeNeiva comGenero(Genero genero) {
        this.genero = genero;
        return this;
    }

    public LivroBuilderModeNeiva comFotoLivro(String fotoLivro) {
        this.fotoLivro = fotoLivro;
        return this;
    }

    public LivroBuilderModeNeiva comDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
        return this;
    }

    public LivroBuilderModeNeiva comUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }


    public LivroBuilderModeNeiva mas() {
        return new LivroBuilderModeNeiva().comId(id).comTitulo(titulo).comAutor(autor).comDetalhes(detalhes).comDisponibilidade(disponibilidade)
                .comFotoLivro(fotoLivro).comGenero(genero).comSinopse(sinopse).comUsuario(usuario)
                .comGenero(genero);
    }

    public Livro build() {
        Livro livro = new Livro();
        livro.setIdLivro(id);
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setSinopse(sinopse);
        livro.setDetalhes(detalhes);
        livro.setFotoLivro(fotoLivro);
        livro.setDisponibilidade(disponibilidade);
        livro.setGenero(genero);
        livro.setUsuario(usuario);
        return livro;
    }
}
