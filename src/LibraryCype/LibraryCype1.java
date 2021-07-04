package LibraryCype;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import LibraryCype.Drama;
import LibraryCype.Romance;

public class LibraryCype1 {

	private ArrayList<Livros> livross;


	public LibraryCype1( ) {
		this.livross = new ArrayList<Livros>();
	}

	public void adicionaLivros(Livros mani) {
		this.livross.add(mani);
	}

	public void listarLivros() {
		for(Livros mani:livross) {
			System.out.println(mani.toString());
		}
		System.out.println("Total = " + this.livross.size() + " livros listados com sucesso!\n");
	}
	
	public void excluirLivros(Livros mani) {
		if (this.livross.contains(mani)) {
			this.livross.remove(mani);
			System.out.println("[Livro " + mani.toString() + "excluido com sucesso!]\n");
		}
		else
			System.out.println("Livro inexistente!\n");
	}

	public void excluirLivros() {
		livross.clear();
		System.out.println("Livros excluidos com sucesso!\n");
	}
	public void gravarLivros()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("C:\\Users\\cynnm\\eclipse-workspace\\LibraryCype.dat"));
			for(Livros mani:livross) {
				outputStream.writeObject(mani);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void recuperarLivros() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\livros.dat"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof Romance)  
					this.livross.add((Romance)obj);
				else if (obj instanceof Drama)  
					this.livross.add((Drama)obj);
			}
		}catch (EOFException ex) {     // when EOF is reached
			System.out.println ("End of file reached");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Livros recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		LibraryCype1 lib  = new LibraryCype1();

		Romance querido    = new Romance ("Querido John",    151, "Nicholas Sparks");
		Romance jane = new Romance ("Orgulho e Preconceito", 784, "Jane Austen");
		Drama  hamlet      = new Drama ("Hamlet",  522, "William Shakespeare");
		Drama  anne     = new Drama ("O Diário de Anne Frank", 413, "Anne Frank");
		lib.adicionaLivros(querido);
		lib.adicionaLivros(jane);
		lib.adicionaLivros(hamlet);
		lib.adicionaLivros(anne);
		lib.listarLivros();
		lib.gravarLivros();
		lib.excluirLivros(querido);
		lib.listarLivros();
		lib.excluirLivros();
		lib.listarLivros();
		lib.recuperarLivros();
		lib.listarLivros();
	}

}