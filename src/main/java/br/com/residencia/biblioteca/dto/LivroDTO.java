package br.com.residencia.biblioteca.dto;



import br.com.residencia.biblioteca.entity.Livro;

public class LivroDTO {
	
	 
	 private Integer id;
	 private String nomeLivro;
	 
	public LivroDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LivroDTO(Livro obj) {
		super();
		this.id = obj.getCodigoLivro();
		this.nomeLivro = obj.getNomeLivro();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeLivro() {
		return nomeLivro;
	}
	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
	

}
