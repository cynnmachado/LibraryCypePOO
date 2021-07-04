

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import LibraryCype.Livros;
import LibraryCype.Romance;
import LibraryCype.Drama;


public class LibraryCype {
	private ArrayList<Livros> livross;

	public LibraryCype() {
		this.livross = new ArrayList<Livros>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Romance leRomance (){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Código", "Autor"};
		valores = leValores (nomeVal);

		int codigo = this.retornaInteiro(valores[1]);

		Romance romance = new Romance (valores[0],codigo,valores[2]);
		return romance;
	}

	public Drama leDrama (){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Código", "Autor"};
		valores = leValores (nomeVal);

		int codigo = this.retornaInteiro(valores[1]);

		Drama drama = new Drama (valores[0],codigo,valores[2]);
		return drama;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // Método estático, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // Não conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;

		//Enquanto não for possível converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaLivros (ArrayList<Livros> livross){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("C:\\Users\\Public\\LibraryCype.project"));
			for (int i=0; i < livross.size(); i++)
				outputStream.writeObject(livross.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Livros> recuperaLivros (){
		ArrayList<Livros> livrossTemp = new ArrayList<Livros>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("C:\\Users\\cynnm\\eclipse-workspace\\LibraryCype.project"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Livros) {
					livrossTemp.add((Livros) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com livros NÃO existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return livrossTemp;
		}
	}

	public void menuLibraryCype (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Livraria\n" +
					"Opções:\n" + 
					"1. Entrar Livros\n" +
					"2. Exibir Livros\n" +
					"3. Limpar Livros\n" +
					"4. Gravar Livros\n" +
					"5. Recuperar Livros\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Livros\n" +
						"Opções:\n" + 
						"1. Drama\n" +
						"2. Romance\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: livross.add((Livros)leDrama());
				break;
				case 2: livross.add((Livros)leRomance());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Livro para entrada NÃO escolhido!");
				}

				break;
			case 2: // Exibir dados
				if (livross.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com um livro primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < livross.size(); i++)	{
					dados += livross.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (livross.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com um livro primeiramente");
					break;
				}
				livross.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (livross.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com um livro primeiramente");
					break;
				}
				salvaLivros(livross);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				livross = recuperaLivros();
				if (livross.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo Livraria Cype");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		LibraryCype lib = new LibraryCype ();
		lib.menuLibraryCype();

	}

}