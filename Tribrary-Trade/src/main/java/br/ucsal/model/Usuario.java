package br.ucsal.model;

import java.util.List;

public class Usuario {

	private Integer idUsuario;
	private String nome;
	private String nomeSocial;
	private String cpf;
	private String email;
	private String telefone;
	private String senha;
	private Integer pontosDeTroca;
	private Endereco endereco;
	private boolean contaAtiva;
	private List<Livro> livros;
	
	public Usuario() {
		
	}

	public Usuario(Integer idUsuario, String nome, String nomeSocial, String cpf, String email, String telefone,
			String senha, Integer pontos,Endereco endereco) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.nomeSocial = nomeSocial;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.pontosDeTroca=pontos;
		this.endereco= endereco;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getPontosDeTroca() {
		return pontosDeTroca;
	}

	public void setPontosDeTroca(Integer pontosDeTroca) {
		this.pontosDeTroca = pontosDeTroca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isContaAtiva() {
		return contaAtiva;
	}

	public void setContaAtiva(boolean contaAtiva) {
		this.contaAtiva = contaAtiva;
	}
	
	
	
}
