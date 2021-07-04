package LibraryCype;

import LibraryCype.Livros;

public class Drama extends Livros {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Faz chorar";
	}
	public Drama(String nome, int codigo, String autor) {
		super(nome, codigo, autor);
		this.genero = "Drama";
	}
}
