package LibraryCype;

import java.io.Serializable;

public abstract class Livros implements Serializable {

	private static final long serialVersionUID = 1L;
	private   String nome;
	private   int codigo;
	private   String autor;
	protected String genero;
	
	public Livros(String nome, int codigo, String autor) {
		this.nome = nome;
		this.codigo = codigo;
		this.autor = autor;
	}
	public String toString() {
		String retorno = "";
		retorno += "Nome: "     + this.nome     + "\n";
		retorno += "C�digo: "    + this.codigo    + "\n";
		retorno += "Autor: "     + this.autor     + "\n";
		retorno += "G�nero: "  + this.genero  + "\n";
		retorno += "Emo��o que causa: "  + soar()        + "\n";
		return retorno;
	}
	public abstract String soar();
}