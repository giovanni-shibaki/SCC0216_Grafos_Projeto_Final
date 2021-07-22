/*
 *	Giovanni Shibaki Camargo - 11796444
 *	Pedro Kenzo Muramatsu Carmo - 11796451
 * =======================================
 * (i) Quantos vértices são vértices fontes (pais desconhecidos) ? (10 pontos)
 *
 */
package ex_final;

public class ItemA {
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
