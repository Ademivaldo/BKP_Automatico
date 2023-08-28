package aplicacao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.PrintStream;

import entidades.Pastas;
import entidades.CopiarPasta;
import entidades.CompararData;

public class App {


	public static void main(String[] args) throws IOException {
		PrintStream consoleLog = new PrintStream(new FileOutputStream("C:\\Users\\ademivaldo\\Desktop\\log.txt"));
		System.setOut(consoleLog);
		System.setErr(consoleLog);

		Pastas pasta = new Pastas();

		List<String> listPastaOrigemNome = new ArrayList<>();
		List<String> listPastaDestinoNome = new ArrayList<>();
		List<String> listPastaDiferenteNome = new ArrayList<>();
		List<Date> listPastaOrigemData = new ArrayList<>();
		List<Date> listPastaDestinoData = new ArrayList<>();

		pasta.setOrigem("C:\\Users\\ademivaldo\\Desktop\\Para Servidor");
		pasta.setDestino("Z:\\BKP");

		File verificaOrigem = new File(pasta.getOrigem());
		File verificaDestino = new File(pasta.getDestino());
		List<File> arquivosEncontradosO = new ArrayList<>();
		List<File> arquivosEncontradosD = new ArrayList<>();

		boolean[] PP = { false, false }; // PP = Proximo passo

		int contadorO = 0;
		int contadorD = 0;

		if (verificaOrigem.exists() && verificaOrigem.isDirectory()) {
			PP[0] = true;

			// Obter a lista de arquivos no diretório
			File[] arquivosO = verificaOrigem.listFiles();

			// Iterar sobre os arquivos
			for (File arquivoO : arquivosO) {
				// se NEGAR ele lista as pastas // se não NEGAR ele// possivel atualização para copiar 
				//somente arquivo modificado...
				if (!arquivoO.isFile()) {
					arquivosEncontradosO.add(arquivoO);
					contadorO++;
				}
			}
			// Obtem a data da ultima modificação
			System.out.println("----------------ORIGEM------------------");
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
				// Verificar se é um arquivo // se NEGAR ele lista as pastas // se não NEGAR ele
				// lista os arquivos
				if (!arquivo.isFile()) {
					arquivosEncontradosD.add(arquivo);
					contadorD++;
				}
			}
			System.out.println("\n\n\n");
			// Obtem a data da ultima modificação
			System.out.println("-----------------DESTINO-----------------");
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

		if (PP[0] && PP[1]) {
			String[] pastaOrigemData = new String[contadorO];
			String[] pastaOrigemNome = new String[contadorO];
			String[] pastaDestinoData = new String[contadorD];
			String[] pastaDestinoNome = new String[contadorD];

			int i = 0;
			for (File arquivo : arquivosEncontradosO) {

				long ultimaModificacaoMili = arquivo.lastModified();
				Date ultimaModificacao = new Date(ultimaModificacaoMili);
				pastaOrigemData[i] = ultimaModificacao.toString();
				pastaOrigemNome[i] = arquivo.getName();
				i++;

			}

			int k = 0;
			for (File arquivo : arquivosEncontradosD) {

				long ultimaModificacaoMili = arquivo.lastModified();
				Date ultimaModificacao = new Date(ultimaModificacaoMili);
				pastaDestinoData[k] = ultimaModificacao.toString();
				pastaDestinoNome[k] = arquivo.getName();
				k++;

			} // aqui, por serem iguais, ele vai fazer apenas a comparação de cada pasta entre
				// origem e destino para saber se existe diferença entre a data de modificação
			if (listPastaOrigemNome.equals(listPastaDestinoNome)) {// quanatidade e nome

				System.out.println("As pastas são iguais na quantidade e no nome das pastas!!\n");
				System.out.println("Vamos verificar as datas!!!\n");

				if (listPastaOrigemData.equals(listPastaDestinoData)) {

					System.out.println("As pastas são iguais na data!!\n");
					System.out.println("Nada a ser feito, obrigado!!\n");
					System.out.println(
							"-----------------------------------------------------------------------------------");
				} else {
					System.out.println("As pastas não são iguais na data!!\n");
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
						System.out.println("OBS: o backup geralmente tem data mais recente por ser copiado ao final ou inicio do dia\n");
					}
				}
			}
			// Nesse caso, além de fazer a mesma coisa do caso anterior, ele também irar
			// criar e copiar os arquivos
			// das pastas diferente entre a raiz e o BKP
			else {

				System.out.println("\nAs pastas NÂO são iguais na quantidade Ou no nome!!\n" + "\nVamos verificar\n");
				System.out.println("\nVamos verificar também se houve alteração em alguma outra pasta!!\n");

				if (listPastaOrigemNome.size() >= listPastaDestinoNome.size()) {
					System.out.println("\nPasta Origem possui mais pastas ou sofreu alteração!!\n");
					for (String elemento : listPastaOrigemNome) {
						if (!listPastaDestinoNome.contains(elemento)) {
							listPastaDiferenteNome.add(elemento);
						}
					}
					listPastaDiferenteNome.addAll(CompararData.listaRetorno(listPastaOrigemNome, listPastaDestinoNome,
							pastaOrigemData, pastaDestinoData));
					try {
						CopiarPasta.CopiaPasta(pasta.getOrigem(), pasta.getDestino(), listPastaDiferenteNome);
						System.out.println("\nFoi copiadas agora!!\n");
						System.out.println("\nLista copiada: " + listPastaDiferenteNome);
					}
					catch (Exception e){
						System.out.println("-> Erro - Pasta Origem possui mais pastas ou sofreu alteração!!\n" + e);
					}

				} else if (listPastaOrigemNome.size() < listPastaDestinoNome.size()) {
					System.out.println("\nPasta BKP possui mais pastas!!\n");
					System.out.println("\nVamos verificar se alguma pasta da origem não tem no destino\n");
					System.out.println("\nVamos verificar também se houve alteração em alguma outra pasta!!\n");

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
							System.out.println("\n1 Pasta foram encontradas em divergencia com o BKP!!!\n");
							try {
								CopiarPasta.CopiaPasta(pasta.getOrigem(), pasta.getDestino(), listPastaDiferenteNome);
								System.out.println("\nFoi copiadas agora!!\n");
								System.out.println("\nLista copiada: " + listPastaDiferenteNome);
							}
							catch (Exception e){
								System.out.println("-> Erro ao achar 1 pasta em divergencia!!\n" + e);
							}
							
							
							break;
						default:
							System.out.println("\n" + t + " Pastas foram encontradas em divergencia com o BKP!!!\n");				
							try {
								CopiarPasta.CopiaPasta(pasta.getOrigem(), pasta.getDestino(), listPastaDiferenteNome);
								System.out.println("\nFoi copiadas agora!!\n");
								System.out.println("\nLista copiada: " + listPastaDiferenteNome);
							}
							catch (Exception e){
								System.out.println("-> Erro ao achar +1 pasta em divergencia!!\n" + e);
							}
						}

					} else {
						System.out.println("\n0 Pastas foram encontradas com nome em divergencia com o BKP!!\n");
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
									"\nOBS: o backup geralmente tem data mais recente por ser copiado após a criação na pasta origem\n");
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
}