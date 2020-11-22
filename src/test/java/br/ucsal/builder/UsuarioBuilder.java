package br.ucsal.builder;

import br.ucsal.model.Endereco;
import br.ucsal.model.Livro;
import br.ucsal.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import static br.ucsal.builder.EnderecoBuilder.umEndereco;
import static br.ucsal.builder.LivroBuilder.umLivroDisponivel;

public class UsuarioBuilder {

    private Usuario usuario;

    private UsuarioBuilder() {
    }

    public static UsuarioBuilder umUsuario() {
        UsuarioBuilder builder = new UsuarioBuilder();
        builder.usuario = new Usuario();
        builder.usuario.setIdUsuario(null);
        builder.usuario.setNome("Usuario Builder");
        builder.usuario.setNomeSocial("Usuario builder Social");
        builder.usuario.setCpf("016.648.658-89");
        builder.usuario.setEmail("userbuilder@email.com");
        builder.usuario.setTelefone("9999-9999");
        builder.usuario.setSenha("123");
        builder.usuario.setPontosDeTroca(50);

        Endereco endereco = umEndereco().agora();

        builder.usuario.setEndereco(endereco);

        List<Livro> livros = new ArrayList<>();
        builder.usuario.setLivros(livros);
        return builder;

    }

    public UsuarioBuilder comEndereco(Endereco endereco) {

        usuario.setEndereco(endereco);

        return this;
    }


    public UsuarioBuilder comNome(String nome) {

        usuario.setNome(nome);

        return this;
    }

    public UsuarioBuilder comNovoLivro(String titulo) {

        Livro livro = umLivroDisponivel().comTitulo(titulo).comUsuario(usuario).agora();
        usuario.getLivros().add(livro);

        return this;
    }

    public UsuarioBuilder comLivro(Livro livro) {

        usuario.getLivros().add(livro);

        return this;
    }

    public Usuario agora() {
        return usuario;

    }
}
