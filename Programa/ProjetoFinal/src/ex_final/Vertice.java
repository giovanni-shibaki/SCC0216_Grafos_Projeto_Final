/*
 *	Giovanni Shibaki Camargo - 11796444
 *	Pedro Kenzo Muramatsu Carmo - 11796451
 */
package ex_final;
import java.util.LinkedList;

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