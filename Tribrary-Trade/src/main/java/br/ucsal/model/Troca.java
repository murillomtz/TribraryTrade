package br.ucsal.model;

import java.time.LocalDate;
import java.util.List;

public class Troca {

	private Integer idTroca;
	private LocalDate data;
	private String local;
	private String status;
	private List<Livro> livros;
	
	public Troca() {
		
	}

	public Troca(Integer idTroca, LocalDate data, String local, String status, List<Livro> livros) {
		super();
		this.idTroca = idTroca;
		this.data = data;
		this.local = local;
		this.status = status;
		this.livros = livros;
	}

	public Integer getIdTroca() {
		return idTroca;
	}

	public void setIdTroca(Integer idTroca) {
		this.idTroca = idTroca;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
}
