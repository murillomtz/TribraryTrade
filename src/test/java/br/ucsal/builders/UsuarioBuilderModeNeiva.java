package br.ucsal.builders;

import br.ucsal.model.Endereco;
import br.ucsal.model.Livro;
import br.ucsal.model.Usuario;

import java.util.List;

public class UsuarioBuilderModeNeiva {


    private static final Integer ID_DEFAULT = null;
    private static final String NOME_DEFAULT = "Usuario Builder";
    private static final String NOME_SOCIAL_DEFAULT = "Nome social Builder";
    private static final String CPF_DEFAULT = "016.648.658-89";
    private static final String EMAIL_DEFAULT = "userbuilder@email.com";
    private static final String TELEFONE_DEFAULT = "9999-9999";
    private static final String SENHA_DEFAULT = "123";
    private static final Integer PONTOS_DE_TROCA_DEFAULT = 50;
    private static final Endereco ENDERECO_DEFAULT = null;
    private static final Boolean CONTA_ATIVA_DEFAULT = Boolean.TRUE;
    private static final List<Livro> LIVROS_DEFAULT = null;


    private Integer id = ID_DEFAULT;
    private String nome = NOME_DEFAULT;
    private String nomeSocial = NOME_SOCIAL_DEFAULT;
    private String cpf = CPF_DEFAULT;
    private String email = EMAIL_DEFAULT;
    private String telefone = TELEFONE_DEFAULT;
    private String senha = SENHA_DEFAULT;
    private Integer pontosDeTroca = PONTOS_DE_TROCA_DEFAULT;
    private Endereco endereco = ENDERECO_DEFAULT;
    private Boolean contaAtiva = CONTA_ATIVA_DEFAULT;
    private List<Livro> livros = LIVROS_DEFAULT;

    public UsuarioBuilderModeNeiva() {
    }

    public static UsuarioBuilderModeNeiva umUsuario() {
        return new UsuarioBuilderModeNeiva();
    }

    public UsuarioBuilderModeNeiva comId(Integer id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilderModeNeiva comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilderModeNeiva comNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
        return this;
    }

    public UsuarioBuilderModeNeiva comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public UsuarioBuilderModeNeiva comEmail(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilderModeNeiva comTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public UsuarioBuilderModeNeiva comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public UsuarioBuilderModeNeiva comPontosDeTroca(Integer pontosDeTroca) {
        this.pontosDeTroca = pontosDeTroca;
        return this;
    }

    public UsuarioBuilderModeNeiva comEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public UsuarioBuilderModeNeiva comContaAtiva(Boolean contaAtiva) {
        this.contaAtiva = contaAtiva;
        return this;
    }

    public UsuarioBuilderModeNeiva comLivros(List<Livro> livros) {
        this.livros = livros;
        return this;
    }

    public UsuarioBuilderModeNeiva mas() {
        return new UsuarioBuilderModeNeiva().comId(id).comNome(nome).comNomeSocial(nomeSocial).comCpf(cpf).comEmail(email)
                .comTelefone(telefone).comSenha(senha).comPontosDeTroca(pontosDeTroca).comEndereco(endereco)
                .comContaAtiva(contaAtiva).comLivros(livros);
    }

    public Usuario build() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(id);
        usuario.setNome(nome);
        usuario.setNomeSocial(nomeSocial);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setSenha(senha);
        usuario.setPontosDeTroca(pontosDeTroca);
        usuario.setEndereco(endereco);
        usuario.setContaAtiva(contaAtiva);
        usuario.setLivros(livros);

        return usuario;
    }
}
