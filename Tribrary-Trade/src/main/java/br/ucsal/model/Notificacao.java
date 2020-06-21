package br.ucsal.model;

import java.util.List;

public class Notificacao {

	private Integer idNotificacao;
	private Livro livroDesejado;
	private Usuario usuarioSolicitante;
	private String status;
	
	private List<Livro> livrosDoUsuario;

	public Notificacao () {
		
	}

	public Notificacao(Integer idNotificacao, Livro livroDesejado, Usuario usuarioSolicitante, String status) {
		super();
		this.idNotificacao = idNotificacao;
		this.livroDesejado = livroDesejado;
		this.usuarioSolicitante = usuarioSolicitante;
		this.status = status;
	}

	public Integer getIdNotificacao() {
		return idNotificacao;
	}

	public void setIdNotificacao(Integer idNotificacao) {
		this.idNotificacao = idNotificacao;
	}

	public Livro getLivroDesejado() {
		return livroDesejado;
	}

	public void setLivroDesejado(Livro livroDesejado) {
		this.livroDesejado = livroDesejado;
	}

	public Usuario getUsuarioSolicitante() {
		return usuarioSolicitante;
	}

	public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Livro> getLivrosDoUsuario() {
		return livrosDoUsuario;
	}

	public void setLivrosDoUsuario(List<Livro> livrosDoUsuario) {
		this.livrosDoUsuario = livrosDoUsuario;
	}
	
}
