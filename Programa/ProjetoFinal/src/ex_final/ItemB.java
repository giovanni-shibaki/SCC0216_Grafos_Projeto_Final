/*
 *	Giovanni Shibaki Camargo - 11796444
 *	Pedro Kenzo Muramatsu Carmo - 11796451
 * =======================================
 * (ii) Quantos v�rtices s�o vertices sorvedouros ? (20 pontos)
 *
 */
package ex_final;

public class ItemB 
{
	public static void main(String[] args) 
	{
		// Fazer a leitura do arquivo pajek e verificar aqueles cuja lista vertices pai est� nula
		
		Vertice vertices[] = FileManager.lerPajek();
		int numVertices = vertices.length;
		
		int numVerticesSorvedouros = 0; // numero de vertices sorvedouros (lista de adjacencia vazia)
		
		// percorrendo o vetor dos vertice do grafo
		for(int i=0; i<numVertices; i++)
		{
      		// contando vertices sorvedouros (lista de adjacencia vazia)
			if(vertices[i].getListaAdjecencia().isEmpty())
			{
				numVerticesSorvedouros++;
			}
		}
		
		// Por fim, printar o n�mero de vertices Fontes
		System.out.println(numVerticesSorvedouros);
	}
}
