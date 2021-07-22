/*
 *	Giovanni Shibaki Camargo - 11796444
 *	Pedro Kenzo Muramatsu Carmo - 11796451
 * =======================================
 * (iii) Quais s�o os v�rtices sorvedouros tais que ambos pais s�o
 * descendentes de v�rtice 1 (DFS) ? (10 pontos)
 * 
 */
package ex_final;

import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class ItemC 
{
    // uma flag para determinar que os pais de um dado vertice sorvedouro foram encontrados na DFS com origem no vertice 1
	private static boolean flag = false;
  
	public static void main(String[] args) 
	{
		// Fazer a leitura do arquivo pajek e verificar aqueles cuja lista vertices pai
		// est� nula

		Vertice vertices[] = FileManager.lerPajek();
		int numVertices = vertices.length;
		int origem = 0; // vertice inicial para a busca em profundidade

		Vector<Integer> verticesSorvedouros = new Vector<Integer>(); // vetor de vertices sorvedouros (lista de
																		// adjacencia vazia)
		// percorrendo o vetor dos vertice do grafo
		for (int i = 0; i < numVertices; i++)
		{
			// registrando os vertices sorvedouros em um vetor
       		// o dfs sera realizado apenas para vertices que possuem no minimo um pai
			if (vertices[i].getListaAdjecencia().isEmpty() && vertices[i].getVerticesPai().size() == 2) 
			{
				verticesSorvedouros.add(i);
			}
		}

		// Agora, realizar a busca em profundidade a partir do v�rtice 1 e verificar se
		// os v�rtices sorvedouros s�o encontrados durante a busca
		for (int i = 0; i < verticesSorvedouros.size(); i++) 
		{
      		// setando as flags 
			boolean pai1 = false, pai2 = false;
			
      		// extrair os dois pais do vertice sorvedouro atual
      		LinkedList<Integer> verticesPai = vertices[verticesSorvedouros.get(i)].getVerticesPai();
      		
			// verificar se o vertice atual possui dois pais que sao descendentes do vertice 1
			buscaEmProfundidade(vertices, numVertices, origem, verticesPai.get(0));
			
			if(flag == true)
				pai1 = true;
			
			// Setar todos os v�rtices como n�o visitados para que as buscas em profundidade possam ser feitas
			resetarVisitados(vertices, numVertices);
			
			// Resetar a flag
			flag = false;
			
			buscaEmProfundidade(vertices, numVertices, origem, verticesPai.get(1));
			
			if(flag == true)
				pai2 = true;
			
			// Resetar a flag
			flag = false;
			
			// Setar todos os v�rtices como n�o visitados para que as buscas em profundidade possam ser feitas
			resetarVisitados(vertices, numVertices);
			
			// Faz a verifica��o se ambos os pais s�o descendentes do v�rtice 1
			if(pai1 == true && pai2 == true)
			{
				System.out.println(verticesSorvedouros.get(i)+1);
			}
		}
	}

	/*
	 * Setar todos os v�rtices como n�o visitados para que as buscas em profundidade possam ser feitas
	 * @param vertices
	 * @param numVertices
	 */
	private static void resetarVisitados(Vertice vertices[], int numVertices)
	{
		for(int k=0; k<numVertices; k++)
		{
			vertices[k].setVisitado(false);
		}
	}
	
	/**
	 * Faz a busca em profundidade a partir do verticeInicial por meio da lista de
	 * adjecncia ll
	 * 
	 * @param ll             Lista de adjecencia
	 * @param numNodes       Numero total de vertices
	 * @param verticeInicial
	 */
	private static void buscaEmProfundidade(Vertice vertices[], int numNodes, int verticeInicial, int verticeBuscado) 
	{
		// Agora, com a lista de adjecencias criada, implementar a busca em profundidade
		Stack<Integer> cinza = new Stack<>();
		LinkedList<Integer> preto = new LinkedList<>();

		// Chamar a fun��o dfs
		dfs(vertices, cinza, preto, verticeInicial, verticeBuscado);
	}

	private static void dfs(Vertice vertices[], Stack<Integer> cinza, LinkedList<Integer> preto, int verticeAtual, int verticeBuscado) 
	{
		// Verificar se o v�rtice atual n�o � aquele buscado
		if(verticeAtual == verticeBuscado)
			flag = true;
		
		// colocar o v�rtice atual na pilha cinza
		vertices[verticeAtual].setVisitado(true);
		cinza.push(verticeAtual);

		int aux = 0;
		int aux2 = 0;

		// percorrendo a lista de vertices adjacentes
		while (aux < (vertices[verticeAtual].getTamanhoListaAdjecencia())) 
		{
			aux2 = vertices[verticeAtual].getVerticeAdjecente(aux);

			// Ver se o vertice j� n�o foi visitado
			if (vertices[aux2].getVisitado() == false) 
			{
				vertices[aux2].setVisitado(true);
				dfs(vertices, cinza, preto, aux2, verticeBuscado);
			}
			aux++;
		}

		// Por fim de todas as chamadas recursivas colocar os v�rtices na lista preta e
		// colocar o tempo de t�rmino de cada um
		aux = cinza.pop();
		preto.addLast(aux);
	}
}
