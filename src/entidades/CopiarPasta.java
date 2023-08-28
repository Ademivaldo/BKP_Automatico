package entidades;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class CopiarPasta {
	Path Origem = null;
	Path Destino = null;
	List<String> pastas = null;
	
	public static void CopiaPasta(String origem, String destino, List<String> pastasDiferentes) throws IOException{
		 Path Origem = Paths.get(origem);
		 Path Destino = Paths.get(destino);
		 List<String> pastas = pastasDiferentes;
	
		 for(int i=0; i<pastas.size(); i++) {
			// Realiza a cópia recursiva do diretório de origem para o destino
			 Path OrigemPasta = Paths.get(origem+"\\"+pastas.get(i));
			
			 Files.walkFileTree(OrigemPasta, new SimpleFileVisitor<Path>() {
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
	}
