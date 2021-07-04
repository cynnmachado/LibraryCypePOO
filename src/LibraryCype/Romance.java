package LibraryCype;

import LibraryCype.Livros;

public class Romance extends Livros {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Faz suspirar";
	}
	public Romance(String nome, int codigo, String autor) {
		super(nome, codigo, autor);
		this.genero = "Romance";
	}
}