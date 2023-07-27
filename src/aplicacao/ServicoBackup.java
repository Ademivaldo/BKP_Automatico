package aplicacao;

import java.io.IOException;
import metodo.CopiarPasta;

public class ServicoBackup {
	
	//CUIDADO, SE A PASTA DESTINO NÃO EXISTIR, ELA SERÁ CRIADA...
	
	public static void main(String[] args) {
		String CaminhoOrigem = "C:\\Users\\ademivaldo\\Desktop\\teste";
		String CaminhoDestino = "\\\\192.168.1.101\\Junior\\BKP";
		
		try {
			CopiarPasta.CopiaPasta(CaminhoOrigem,CaminhoDestino);
			System.out.println("Pasta copiada com sucesso!");
		} catch (IOException e) {
            System.out.println("Pasta de Origem não localizada no caminho especificado: " + e);//e.printStackTrace();
        }
    }
}
