package aplicacao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import entidades.Pastas;

import entidades.VerificaArquivos;
import entidades.VerificaPasta;
public class App {


	public static void main(String[] args) throws IOException {
		PrintStream consoleLog = new PrintStream(new FileOutputStream("C:\\Users\\ademivaldo\\Desktop\\Backup.txt"));// Coloque o caminho para a saida do log
		System.setOut(consoleLog);
		System.setErr(consoleLog);

		Pastas pasta = new Pastas();

		pasta.setOrigem("C:\\Users\\ademivaldo\\Desktop\\Para Servidor");
		pasta.setDestino("Z:\\BKP");
		
		File verificaOrigem = new File(pasta.getOrigem());
		File verificaDestino = new File(pasta.getDestino());
		

		VerificaArquivos.VerificaArquivos(verificaOrigem, verificaDestino, pasta);
		VerificaPasta.VerificaPasta(verificaOrigem, verificaDestino, pasta);
		
	}
}