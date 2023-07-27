package metodo;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CopiarPasta {
	Path Origem = null;
	Path Destino = null;
	
	public static void CopiaPasta(String origem, String destino) throws IOException{
		 Path Origem = Paths.get(origem);
		 Path Destino = Paths.get(destino);
	
	
	// Realiza a cópia recursiva do diretório de origem para o destino
		 Files.walkFileTree(Origem, new SimpleFileVisitor<Path>() {
	         @Override
	         //								inicia um path "Basicamente faz o atributo virar o nome de caminho"
	         public FileVisitResult visitFile(Path Pasta, BasicFileAttributes attrs) throws IOException {
	             Path targetPath = Destino.resolve(Origem.relativize(Pasta)); //caminho origem vira caminho Pasta em um 
	             //formato compreensivel
	             //Files pacote -> .copy copia os arquivos
	             // Pasta -> caminho onde vai começar a leitura
	             // target -> destinho de leitura //abaixo é o comando para refazer se existir
	             Files.copy(Pasta, targetPath, StandardCopyOption.REPLACE_EXISTING);
	             return FileVisitResult.CONTINUE; // só pra retornar como terminado
	         }
	
	         @Override
	         // basicamente a mesma coisa que o de cima -> só que é no caminho de destino
	         public FileVisitResult preVisitDirectory(Path Pasta, BasicFileAttributes attrs) throws IOException {
	             Path targetPath = Destino.resolve(Origem.relativize(Pasta));
	             Files.createDirectories(targetPath);
	             return FileVisitResult.CONTINUE;
	         }
	     });
		}
	}
