/*
 *	Giovanni Shibaki Camargo - 11796444
 *	Pedro Kenzo Muramatsu Carmo - 11796451
 * =======================================
 * (iv) Seja d o vetor de distâncias de cada vértice em relação ao vértice 1. Seja X e Y os pais de um dado vértice obtido na
 * parte c. Quais são os vértices na parte (c) tais que a soma d[X] + d[Y] é o mínimo ? 
 * Caso o vértice 1 seja portador de uma mutação associada com uma doença genética, 
 * esses indivíduos na parte (c) possuem um risco maior de serem afetados.
 * 
 */
package ex_final;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class ItemD 
{

    // uma flag para determinar que os pais de um dado vertice sorvedouro foram encontrados na DFS com origem no vertice 1
	private static boolean flag = false;
  
	public static void main(String[] args) 
	{
    	// Fazer a leitura do arquivo pajek e verificar aqueles cuja lista vertices pai
		// está nula

		Vertice vertices[] = FileManager.lerPajek();
		int numVertices = vertices.length;
		int origem = 0; // vertice inicial para a busca em largura
	
		// vertices que sao resultado do item C
		Vector<Integer> verticesParteC = itemC(vertices, numVertices, origem);
		Vector<Integer> distanciaPaisVerticesParteC = new Vector<Integer>();
		
		// Chamar a busca em largura para determinar as distâncias dos vértices
		buscaEmLargura(vertices, numVertices, 0);
		
		// Pegar as distancias dos vertices pais dos vertices retornados do item C e verificar
		// Se a sua soma é igual à minima distância dos vértices após a busca em Largura
		for(int i=0; i<verticesParteC.size(); i++)
		{
			LinkedList<Integer> verticesPai = vertices[verticesParteC.get(i)].getVerticesPai();
			
			// Somar a distancia dos pais
			int somaDist = vertices[verticesPai.get(0)].getDistancia() + vertices[verticesPai.get(1)].getDistancia();
			
			// Substituir os valores em verticesParteC pela soma as distancias de seus pa
			distanciaPaisVerticesParteC.add(i, somaDist);
		}
		
		// Pegar o valor mínimo dentro de distanciaPaisVerticesParteC
		int min = Integer.MAX_VALUE;
		for(int i=0; i<verticesParteC.size(); i++)
		{
			if(distanciaPaisVerticesParteC.get(i) < min)
				min = distanciaPaisVerticesParteC.get(i);
		}
		
		// Agora, printar todos os vértices cuja distancia é igual à minima encontrada
		for(int i=0; i<verticesParteC.size(); i++)
		{
			if(distanciaPaisVerticesParteC.get(i) == min)
				System.out.println(verticesParteC.get(i)+1);
		}
	}
	
	/*
	 * esta funcao eh equivalente da
	*/
	public static Vector<Integer> itemC(Vertice vertices[], int numVertices, int origem)
	{
		// saida desejada do Item C
		Vector<Integer> saida  = new Vector<Integer>();																

   		// vetor de vertices sorvedouros (lista de adjacencia vazia)
    	Vector<Integer> verticesSorvedouros = new Vector<Integer>();
		
		// percorrendo o vetor dos vertice do grafo
		for (int i = 0; i < numVertices; i++)
		{
			// registrando os vertices sorvedouros em um vetor
       		// o dfs sera realizado apenas para vertices que possuem no minimo um pai
			if (vertices[i].getListaAdjecencia().isEmpty() && vertices[i].getVerticesPai().size() >= 1) 
			{
				verticesSorvedouros.add(i);
			}
		}

		// Agora, realizar a busca em profundidade a partir do vértice 1 e verificar se
		// os vértices sorvedouros são encontrados durante a busca
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
			
			// Setar todos os vértices como não visitados para que as buscas em profundidade possam ser feitas
			resetarVisitados(vertices, numVertices);
			
			// Resetar a flag
			flag = false;
			
			buscaEmProfundidade(vertices, numVertices, origem, verticesPai.get(1));
			
			if(flag == true)
				pai2 = true;
			
			// Resetar a flag
			flag = false;
			
			// Setar todos os vértices como não visitados para que as buscas em profundidade possam ser feitas
			resetarVisitados(vertices, numVertices);
			
			// Faz a verificação se ambos os pais são descendentes do vértice 1
			if(pai1 == true && pai2 == true)
			{
				saida.add(verticesSorvedouros.get(i));
			}
		}	
		return saida;
  }
	
	/*
	 * Setar todos os vértices como não visitados para que as buscas em profundidade possam ser feitas
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

		// Chamar a função dfs
		dfs(vertices, cinza, preto, verticeInicial, verticeBuscado);
	}

	private static void dfs(Vertice vertices[], Stack<Integer> cinza, LinkedList<Integer> preto, int verticeAtual, int verticeBuscado) 
	{
		// Verificar se o vértice atual não é aquele buscado
		if(verticeAtual == verticeBuscado)
			flag = true;
		
		// colocar o vértice atual na pilha cinza
		vertices[verticeAtual].setVisitado(true);
		cinza.push(verticeAtual);

		int aux = 0;
		int aux2 = 0;

		// percorrendo a lista de vertices adjacentes
		while (aux < (vertices[verticeAtual].getTamanhoListaAdjecencia())) 
		{
			aux2 = vertices[verticeAtual].getVerticeAdjecente(aux);

			// Ver se o vertice já não foi visitado
			if (vertices[aux2].getVisitado() == false) 
			{
				vertices[aux2].setVisitado(true);
				dfs(vertices, cinza, preto, aux2, verticeBuscado);
			}
			aux++;
		}

		// Por fim de todas as chamadas recursivas colocar os vértices na lista preta e
		// colocar o tempo de término de cada um
		aux = cinza.pop();
		preto.addLast(aux);
	}
	
	// ===================================================================================
	
	/**
	 * Faz a busca em largura a partir do verticeInicial por meio da lista de adjecncia ll
	 * @param ll Lista de adjecencia
	 * @param numNodes Numero de vertices
	 * @param verticeInicial Vertice inicial para calculo das distancias
	 */
	private static void buscaEmLargura(Vertice vertices[], int numNodes, int verticeInicial)
	{
		// Agora, com a lista de adjecencias criada, implementar a busca em largura
		Queue<Integer> cinza = new LinkedList<>();
		LinkedList<Integer> preto = new LinkedList<>();
		LinkedList<Integer> branco = new LinkedList<>();
				
		// Adicionar todos os vertices do grafo na lista branca
		for(int j=0; j<numNodes; j++)
		{
			branco.add(j);
		}
				
		// Variaveis auxiliares para vertices
		int aux = verticeInicial;
		int aux2 = 0;
		int aux3 = 0;
		
		// Comeca colocando a distancia do verticeAtual dele mesmo como 0, tira da lista BRANCO e insere da fila CINZA 
		vertices[aux].setDistancia(0);
		branco.set(aux, -1);
		cinza.add(aux);
				
		while(!cinza.isEmpty())
		{
			// Remove o vértice do comeco da fila e o insere na lista PRETO
			aux = cinza.remove();
			preto.add(aux);
			// Recebe o numero de vertices adjecentes a aquele que foi removido da fila CINZA
			aux3 = 0;
			while(aux3 < vertices[aux].getTamanhoListaAdjecencia())
			{
				// aux contem o vertice que esta na posicao aux3 da lista de adjecencias do vertice aux
				aux2 = vertices[aux].getListaAdjecencia().get(aux3);
				// Ver se o vertice esta na lista BRANCO
				if(branco.get(aux2) != -1)
				{
					// A distancia do vertice atual sera igual a distancia do anterior + 1
					vertices[aux2].setDistancia(vertices[aux].getDistancia() + 1);
					cinza.add(branco.get(aux2));
					branco.set(aux2, -1);
				}
				aux3++;
			}
		}
	}
}
