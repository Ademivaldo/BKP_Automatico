package entidades;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class VerificaArquivos {
	static boolean[] PP = { false, false }; // PP = Proximo passo 
	static int contadorD = 0;
	static int contadorO = 0;
	
	static List<File> arquivosEncontradosO = new ArrayList<>();
	static List<File> arquivosEncontradosD = new ArrayList<>();
	
	static List<String> listPastaOrigemNome = new ArrayList<>();
	static List<String> listPastaDestinoNome = new ArrayList<>();
	
	static List<Date> listPastaOrigemData = new ArrayList<>();
	static List<Date> listPastaDestinoData = new ArrayList<>();
	
	static List<String> listPastaDiferenteNome = new ArrayList<>();
	
	public static void VerificaArquivos(File Origem, File Destino, Pastas pasta ) {
		File verificaOrigem = Origem;
		File verificaDestino = Destino;
		
		


		if (verificaOrigem.exists() && verificaOrigem.isDirectory()) {
			PP[0] = true;

			// Obter a lista de arquivos no diretório
			File[] arquivosO = verificaOrigem.listFiles();

			// Iterar sobre os arquivos
			for (File arquivoO : arquivosO) {
				// se NEGAR ele lista as pastas 
				// se não NEGAR lista arquivos
				
				if (arquivoO.isFile()) {
					arquivosEncontradosO.add(arquivoO);
					contadorO++;
				}
			}
			// Obtem a data da ultima modificação
			System.out.println("----------------Arquivos------------------\n");
			System.out.println("----------------ORIGEM------------------\n");
			for (File arquivo : arquivosEncontradosO) {
				long ultimaModificacaoMili = arquivo.lastModified();
				Date ultimaModificacao = new Date(ultimaModificacaoMili);
				System.out.println("Nome do arquivo: " + arquivo.getName());
				System.out.println("Caminho completo: " + arquivo.getAbsolutePath());
				System.out.println("Data Ultima alteração: " + ultimaModificacao);
				System.out.println("Tamanho do arquivo: " + arquivo.length() + " bytes");
				System.out.println("----------------------------------");
				String t = arquivo.getAbsolutePath().replaceAll("\\\\", "¨");
				int temp = t.lastIndexOf("¨");
				String nomePastaO = t.substring(temp + 1);
				listPastaOrigemNome.add(nomePastaO);
				listPastaOrigemData.add(ultimaModificacao);

			}

		} else {
			System.out.println("\n\nO diretorio de Origem não Existe!\n\n");
		}

		if (verificaDestino.exists() && verificaDestino.isDirectory()) {
			PP[1] = true;

			File[] arquivosD = verificaDestino.listFiles();
			for (File arquivo : arquivosD) {
				
				if (arquivo.isFile()) {
					arquivosEncontradosD.add(arquivo);
					contadorD++;
				}
			}
			System.out.println("\n\n\n");
			// Obtem a data da ultima modificação
			System.out.println("-----------------DESTINO-----------------\n");
			for (File arquivo : arquivosEncontradosD) {
				long ultimaModificacaoMili = arquivo.lastModified();
				Date ultimaModificacao = new Date(ultimaModificacaoMili);
				System.out.println("Nome do arquivo: " + arquivo.getName());
				System.out.println("Caminho completo: " + arquivo.getAbsolutePath());
				System.out.println("Data Ultima alteração: " + ultimaModificacao);
				System.out.println("Tamanho do arquivo: " + arquivo.length() + " bytes");
				System.out.println("----------------------------------");
				String t = arquivo.getAbsolutePath().replaceAll("\\\\", "¨");
				int temp = t.lastIndexOf("¨");
				String nomePastaD = t.substring(temp + 1); 
				listPastaDestinoNome.add(nomePastaD);
				listPastaDestinoData.add(ultimaModificacao);

			}
		} else {
			System.out.println("\n\nO diretorio de Destino não Existe!\n\n");
		}
		System.out.println("\n------------------------------------------------------------\n");
		System.out.println("Arquivos Encontrados Origem: " + contadorO);
		System.out.println("Arquivos Encontrados Destino: " + contadorD);
		System.out.println("\n------------------------------------------------------------\n");
		
		if (VerificaArquivos.Retorno() == 1) {
			String[] pastaOrigemData = new String[VerificaArquivos.RetornoOrigem()];
			String[] pastaOrigemNome = new String[VerificaArquivos.RetornoOrigem()];
			String[] pastaDestinoData = new String[VerificaArquivos.RetornoDestino()];
			String[] pastaDestinoNome = new String[VerificaArquivos.RetornoDestino()];

			int i = 0;
			for (File arquivo : RetornoPastasEncontradasO()) {

				long ultimaModificacaoMili = arquivo.lastModified();
				Date ultimaModificacao = new Date(ultimaModificacaoMili);
				pastaOrigemData[i] = ultimaModificacao.toString();
				pastaOrigemNome[i] = arquivo.getName();
				i++;

			}

			int k = 0;
			for (File arquivo : RetornoPastasEncontradasD()) {

				long ultimaModificacaoMili = arquivo.lastModified();
				Date ultimaModificacao = new Date(ultimaModificacaoMili);
				pastaDestinoData[k] = ultimaModificacao.toString();
				pastaDestinoNome[k] = arquivo.getName();
				k++;

			} // aqui, por serem iguais, ele vai fazer apenas a comparação de cada pasta entre
				// origem e destino para saber se existe diferença entre a data de modificação
			if (listPastaOrigemNome.equals(listPastaDestinoNome)) {// quanatidade e nome

				System.out.println("Os arquivos são iguais na quantidade e  nome!!\n");
				System.out.println("Vamos verificar as datas!!!\n");

				if (listPastaOrigemData.equals(listPastaDestinoData)) {

					System.out.println("Os arquivos são iguais na data!!\n");
					System.out.println("Nada a ser feito, obrigado!!\n");
					System.out.println(
							"-----------------------------------------------------------------------------------");
				} else {
					System.out.println("OS arquivos não são iguais na data!!\n");
					System.out.println("Vamos verficar\n");
					System.out.println("TESTANDO...\n");

					listPastaDiferenteNome.addAll(CompararData.listaRetorno(listPastaOrigemNome, listPastaDestinoNome,
							pastaOrigemData, pastaDestinoData));

					if (listPastaDiferenteNome.size() > 0) {
						try {
							CopiarPasta.CopiaPasta(pasta.getOrigem(), pasta.getDestino(), listPastaDiferenteNome);
							System.out.println("\nFoi copiadas agora!!\n");
							System.out.println("\nLista copiada: " + listPastaDiferenteNome);
						}
						catch (Exception e){
							System.out.println("-> Erro - Pasta Origem > 0 !!\n" + e);
						}
					} else {
						System.out.println("Nada a ser copiado! Pasta de Destino possui datas mais Recentes\n");
						System.out.println("OBS: o Backup geralmente tem data mais recente por ser copiado\n");
					}
				}
			}
			// Nesse caso, além de fazer a mesma coisa do caso anterior, ele também irar
			// criar e copiar os arquivos
			// das pastas diferente entre a raiz e o BKP
			else {

				System.out.println("\nOs arquivos NÂO são iguais na quantidade ou  nome!!\n" + "\nVamos verificar\n");
				System.out.println("\nVamos verificar também se houve alteração em algum arquivo!!\n");

				if (listPastaOrigemNome.size() >= listPastaDestinoNome.size()) {
					System.out.println("\nPasta Origem possui arquivos novos ou algum arquivo foi alterado!!\n");
					for (String elemento : listPastaOrigemNome) {
						if (!listPastaDestinoNome.contains(elemento)) {
							listPastaDiferenteNome.add(elemento);
						}
					}
					listPastaDiferenteNome.addAll(CompararData.listaRetorno(listPastaOrigemNome, listPastaDestinoNome,
							pastaOrigemData, pastaDestinoData));
					try {
						CopiarPasta.CopiaPasta(pasta.getOrigem(), pasta.getDestino(), listPastaDiferenteNome);
						System.out.println("\nCopia realizada!!\n");
						System.out.println("\nLista copiada: " + listPastaDiferenteNome);
					}
					catch (Exception e){
						System.out.println("-> Erro - Pasta Origem possui mais pastas ou sofreu alteração!!\n" + e);
					}

				} else if (listPastaOrigemNome.size() < listPastaDestinoNome.size()) {
					System.out.println("\nPasta de BackUp possui mais arquivos!!\n");
					System.out.println("\nVamos verificar se algum arquivo da origem não tem no destino\n");
					System.out.println("\nVamos verificar também se houve alteração em algum arquivo!!\n");

					int t = 0;

					listPastaDiferenteNome.addAll(CompararData.listaRetorno(listPastaOrigemNome, listPastaDestinoNome,
							pastaOrigemData, pastaDestinoData));

					if (listPastaDiferenteNome.size() > 0) {
						t++;
					}
					for (String elemento : listPastaOrigemNome) {
						if (!listPastaDestinoNome.contains(elemento)) {
							listPastaDiferenteNome.add(elemento);
							t++;
						}
					}
					if (t > 0) {

						switch (t) {

						case 1:
							System.out.println("\n1 Arquivo foi encontradas em divergencia com o Backup!!!\n");
							try {
								CopiarPasta.CopiaPasta(pasta.getOrigem(), pasta.getDestino(), listPastaDiferenteNome);
								System.out.println("\nFoi copiado agora!!\n");
								System.out.println("\nLista copiada: " + listPastaDiferenteNome);
							}
							catch (Exception e){
								System.out.println("-> Erro ao achar 1 pasta em divergencia!!\n" + e);
							}
							
							
							break;
						default:
							System.out.println("\n" + t + " Arquivos foram encontrados em divergencia com o Backup!!!\n");				
							try {
								CopiarPasta.CopiaPasta(pasta.getOrigem(), pasta.getDestino(), listPastaDiferenteNome);
								System.out.println("\nForam copiados agora!!\n");
								System.out.println("\nLista copiada: " + listPastaDiferenteNome);
							}
							catch (Exception e){
								System.out.println("-> Erro ao achar +1 pasta em divergencia!!\n" + e);
							}
						}

					} else {
						System.out.println("\n0 Arquivo encontrados com titulo divergente com o Backup!!\n");
						System.out.println("\nVamos verificar as datas!!\n");

						listPastaDiferenteNome.addAll(CompararData.listaRetorno(listPastaOrigemNome,
								listPastaDestinoNome, pastaOrigemData, pastaDestinoData));

						if (listPastaDiferenteNome.size() > 0) {
							try {
								CopiarPasta.CopiaPasta(pasta.getOrigem(), pasta.getDestino(), listPastaDiferenteNome);
								System.out.println("\nFoi copiadas agora!!\n");
								System.out.println("\nLista copiada: " + listPastaDiferenteNome);
							}
							catch (Exception e){
								System.out.println("-> Erro ao Comparar data em pasta com divergencia!!\n" + e);
							}
						} else {
							System.out.println("\nNada a ser copiado! Pasta de Destino possui datas mais Recentes\n");
							System.out.println(
									"\nOBS: o backup geralmente tem data mais recente por ser copiado \n");
						}

					}

				} else {
					for (String elemento : listPastaOrigemNome) {
						if (!listPastaDestinoNome.contains(elemento)) {
							listPastaDiferenteNome.add(elemento);
						}
					}
					try {
						CopiarPasta.CopiaPasta(pasta.getOrigem(), pasta.getDestino(), listPastaDiferenteNome);
						System.out.println("\nMesma quantidade, nomes diferentes!! Foram copiadas agora");
						System.out.println("\nLista copiada: " + listPastaDiferenteNome);
					}
					catch (Exception e){
						System.out.println("-> Erro ao Comparar data em pasta com divergencia!!\n" + e);
					}
					
				}
			}

		}
	}
	
	public static int Retorno() {
		int x = 0; 
		if (PP[0] && PP[1]) {
			x = 1;
		}
		return x;
	}
	
	public static int RetornoOrigem() {
		int x = contadorO;
		return x;
	}
	
	public static int RetornoDestino() {
		int x = contadorD;
		return x;
	}
	
	public static List<File> RetornoPastasEncontradasO(){
		List<File> Retorno = arquivosEncontradosO;
		return Retorno;
	}
	
	public static List<File> RetornoPastasEncontradasD(){
		List<File> Retorno = arquivosEncontradosD;
		return Retorno;
	}
	
	public static List<String> RetornoListPastaOrigemNome(){
		List<String> Retorno = listPastaOrigemNome;
		return Retorno;
	}
	
	public static List<String> RetornoListPastaDestinoNome(){
		List<String> Retorno = listPastaDestinoNome;
		return Retorno;
	}
	
	public static List<Date> RetornoListPastaOrigemData(){
		List<Date> Retorno = listPastaOrigemData;
		return Retorno;
	}
	
	public static List<Date> RetornoListPastaDestinoData(){
		List<Date> Retorno = listPastaDestinoData;
		return Retorno;
	}
}
