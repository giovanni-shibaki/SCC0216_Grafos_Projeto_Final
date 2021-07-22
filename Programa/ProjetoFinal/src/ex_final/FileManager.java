/*
 *	Giovanni Shibaki Camargo - 11796444
 *	Pedro Kenzo Muramatsu Carmo - 11796451
 *
 */
package ex_final;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class FileManager 
{
	public static Vertice[] lerPajek()
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		// Recebendo nome do arquivo Pajek
		String fileName = null;
		try {
			fileName = br.readLine(); 
		}catch(Exception err)
		{
			System.out.println("Erro ao receber nome do arquivo! Saindo...");
			System.exit(0);
		}
		
		// Abrindo o arquivo Pajek para leitura
		Scanner scanner = openReadFile(fileName);
		
		// Le a primeira linha do pajek e armazena o total de Vertices
		int numNodes = 0;
		String ret = scanner.nextLine();
		Scanner scanner2 = new Scanner(ret);
		while(scanner2.hasNext())
		{
			if(scanner2.hasNextInt())
				numNodes = scanner2.nextInt();
			else
				scanner2.next();
		}
		
		// Criar o vetor de Linked List para guardar as listas de vertices adjecentes
		Vertice vertices[] = new Vertice[numNodes];
		
		// Inicializar v�rtices
		for(int i=0; i<numNodes; i++)
		{
			vertices[i] = new Vertice();
		}
		
		// Ler a segunda linha para determinar se o grafo é direcionado ou não
		ret = scanner.nextLine();
		boolean direcionado = false;
		
		if(ret.contains("*Arcs"))
			direcionado = true;
			
		// Comecar a ler as linhas do arquivo para criar o vetor
		int i = 0;
		int j = 0;
		while(scanner.hasNextLine())
		{
			ret = scanner.nextLine();
			if(ret.length() >= 2) // Verifica se a linha lida é válida
			{
				scanner2 = new Scanner(ret);
				i = (scanner2.nextInt()-1);
				j = (scanner2.nextInt()-1);
				// Adiciona o vertice J a lista de vertices adjecentes
				vertices[i].adicionaListaAdjecencia(j);
				
				// Adicionar o vértice i como pai do vértice j, não aceita repetições de um mesmo pai para o vértice
				if(!vertices[j].getVerticesPai().contains(i))
					vertices[j].adicionaVerticePai(i);
				
				// Se for direcionado, não deve existir o caminho contrário
				if(!direcionado)
				{
					vertices[j].adicionaListaAdjecencia(i);
					
					// Adicionar o vértice i como pai do vértice j, não aceitando repetições de um mesmo pai para vértice
					if(!vertices[i].getVerticesPai().contains(j))
						vertices[i].adicionaVerticePai(j);
				}
			}
		}
		scanner.close();
		scanner2.close();
		
		// Retorna o vetor de vértices
		return vertices;
	}
	
	/**
	 * Faz a leitura do arquivo de entrada do programa e abre o arquivo para leitura
	 * @param fileName Nome do arquivo a ser aberto
	 * @return
	 */
	public static Scanner openReadFile(String fileName)
	{
		Scanner scanner = null;
		URL path = Vertice.class.getResource(fileName);
		try
		{
			scanner = new Scanner((new File(path.getFile())));
		}catch(FileNotFoundException fe)
		{
			System.out.println("Arquivo de nome '"+fileName+"' nao encontrado!");
		}
		return scanner;
	}
}
