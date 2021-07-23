/*
 *	Giovanni Shibaki Camargo - 11796444
 *	Pedro Kenzo Muramatsu Carmo - 11796451
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

class Main 
{
	public static void main(String[] args) 
	{
		// Fazer a leitura do arquivo pajek e verificar aqueles cuja lista vertices pai está nula
		
		Vertice vertices[] = FileManager.lerPajek();
		int numVertices = vertices.length;
		
		int numVerticesFontes = 0; // numero de vertices que possuem pais desconhecidos
		
		// percorrendo o vetor dos vertice do grafo
		for(int i=0; i<numVertices; i++)
		{
      		// contando vertices fonte (pais desconhecidos, com lista de adjacencia não vazia)
			if(vertices[i].getVerticesPai().isEmpty() && !vertices[i].getListaAdjecencia().isEmpty())
			{
				numVerticesFontes++;
			}
		}
		
		// Por fim, printar o número de vertices Fontes
		System.out.println(numVerticesFontes);
	}
}

/**
 * Classe para abertura do arquivo PAJEK, leitura dos vértices e criação das listas de adjacencia
 * @author giova
 *
 */
class FileManager 
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

/**
 * Classe vértice para o armazenamento das listas de adjecencia, tempos de descoberta e término
 * @author giova
 *
 */
class Vertice
{
  	// Propriedades de um dado vertice
	private int verticeId;
	private LinkedList<Integer> listaAdjacencia;
	private int distancia;
	private int tempoDescoberta;
	private int tempoTermino;
	private boolean visitado;
	private boolean raiz;	// Serve para a construção da árvore na busca de profundidade
	private LinkedList<Integer> verticesPai;
	
	// Construtor
	public Vertice()
	{
		listaAdjacencia = new LinkedList<>();
		tempoDescoberta = 0;
		tempoTermino = 0;
		visitado = false;
		raiz = false;
		verticesPai = new LinkedList<>();
	}
	
	// Getters e Setters
	public LinkedList<Integer> getListaAdjecencia()
	{
		return listaAdjacencia;
	}
	
	public int getDistancia()
	{
		return distancia;
	}
	
	public int getVerticeId()
	{
		return verticeId;
	}
	
	public int getTempoDescoberta()
	{
		return tempoDescoberta;
	}
	
	public int getTempoTermino()
	{
		return tempoTermino;
	}
	
	public boolean getVisitado()
	{
		return visitado;
	}
	
	public boolean getRaiz()
	{
		return raiz;
	}
	
	public LinkedList<Integer> getVerticesPai()
	{
		return verticesPai;
	}
	
	public void setDistancia(int x)
	{
		this.distancia = x;
	}
	
	public void setVerticeId(int x)
	{
		if(x >= 0)
			verticeId = x;
	}
	
	public void setTempoDescoberta(int x)
	{
		if(x > 0)
			tempoDescoberta = x;
	}
	
	public void setTempoTermino(int x)
	{
		if(x > 0)
			tempoTermino = x;
	}
	
	public void setVisitado(boolean x)
	{
		visitado = x;
	}
	
	public void setRaiz(boolean x)
	{
		raiz = x;
	}
	
	public void adicionaVerticePai(int x)
	{
		verticesPai.add(x);
	}
	
	// Métodos
	
	public void printaListaAdjecencia()
	{
		for(int j=0; j<listaAdjacencia.size(); j++)
		{
			System.out.print("["+(listaAdjacencia.get(j)+1)+"]");
		}
	}
	
	public void adicionaListaAdjecencia(int x)
	{
		listaAdjacencia.add(x);
	}
	
	public int getTamanhoListaAdjecencia()
	{
		return listaAdjacencia.size();
	}
	
	public int getVerticeAdjecente(int index)
	{
		return listaAdjacencia.get(index);
	}
}
