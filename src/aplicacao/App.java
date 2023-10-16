package aplicacao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import entidades.Pastas;

import metodo.VerificaArquivos;
import metodo.VerificaPasta;
public class App {


	public static void main(String[] args) throws IOException {
		PrintStream consoleLog = new PrintStream(new FileOutputStream("D:\\Users\\78_tic\\Desktop\\Para Servidor\\teste.txt"));// Coloque o caminho para a saida do log
		System.setOut(consoleLog);
		System.setErr(consoleLog);

		Pastas pasta = new Pastas();

		pasta.setOrigem("D:\\Users\\78_tic\\Documents\\Gerais");
		pasta.setDestino("D:\\Users\\78_tic\\Documents\\Documentos");
		
		File verificaOrigem = new File(pasta.getOrigem());
		File verificaDestino = new File(pasta.getDestino());
		

		VerificaArquivos.VerificaArquivos(verificaOrigem, verificaDestino);
		VerificaPasta.VerificaPasta(verificaOrigem, verificaDestino, pasta);
		
	}
}