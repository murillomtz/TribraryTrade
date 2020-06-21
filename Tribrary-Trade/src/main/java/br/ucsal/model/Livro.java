package br.ucsal.model;

public class Livro {

	private Integer idLivro;
	private String titulo;
	private String autor;
	private String sinopse;
	private String detalhes;
	private String fotoLivro;
	private boolean disponibilidade;
	private Genero genero;
	private Usuario usuario;

	public Livro() {

	}

	public Livro(Integer idLivro, String titulo, String autor, String sinopse, String detalhes, String fotoLivro,
			Genero genero, Usuario usuario) {
		super();
		this.idLivro = idLivro;
		this.titulo = titulo;
		this.autor = autor;
		this.sinopse = sinopse;
		this.detalhes = detalhes;
		this.fotoLivro = fotoLivro;
		this.genero = genero;
		this.usuario = usuario;
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public String getFotoLivro() {
		return fotoLivro;
	}

	public void setFotoLivro(String fotoLivro) {
		this.fotoLivro = fotoLivro;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	@Override
	public String toString() {
		return "Livro [idLivro=" + idLivro + ", titulo=" + titulo + ", autor=" + autor + ", sinopse=" + sinopse
				+ ", detalhes=" + detalhes + ", fotoLivro=" + fotoLivro + ", genero=" + genero.getNome() + ", usuario=" + usuario.getNome()
				+ "]";
	}

}
