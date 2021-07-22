# SCC0216 - Modelagem Computacional em Grafos: Projeto Final
- Repositório dedicado ao projeto final da matéria de Modelagem Computacional em Grafos - 2021.1
- Docente responsável: Kuruvilla J. Abraham

## Enunciado

Uma genealogia pode ser representada por meio de um DAG.
Esses DAGS possuem vários vértices fontes e vértices
sorvedouros. O número total de vértices é igual ao número de
indivíduos na genealogia. Todos os vértices possuem grau de
entrada 2 (dois pais) ou zero (pais desconhecidos). Os vértices
sorvedouros são aqueles indivíduos sem filhos. Se indivíduo Y
é um descendente de indivíduo X, o vértice Y é alcançável por
meia de uma busca em profundidade a partir do vértice X.
Z é um dos pais do W se e somente se existe uma aresta
Z → W.

## Exercícios

1. Quantos vértices são vértices fontes (pais desconhecidos) ? *(10 pontos)*

2. Quantos vértices são vertices sorvedouros ? *(20 pontos)

3. Quais são os vértices sorvedouros tais que ambos pais são descendentes de vértice 1 (DFS) ? *(10 pontos)*

4. Seja d o vetor de distâncias de cada vértice em relação ao vértice 1. Seja X e Y os pais de um dado vértice obtido na parte c. 
    - Quais são os vértices na parte (c) tais que a soma d[X] + d[Y] é o mínimo ?  Caso o vértice 1 seja portador de uma mutação associada com uma doença genética, esses indivíduos na parte (c) possuem um risco maior de serem afetados. (Talvez mais fácil por meio de uma BFS. (10 pontos)

## Solução

### Item A

- Montar os vértices e as listas de adjacencia
- Verificar quantos vértices não possuem vértice pai

### Item B

- Montar os vértices e as listas de adjacencia
- Verificar quantos vértices possuem 0 vértices em sua lista de adjacencia

### Item C

- Montar os vértices e as listas de adjacencia
- Mostrar os vértices cujo ambos os pais são decendentes do vértice 1
  - Isto é, realizar uma busca em profundidade a partir do vértice 1, e caso encontre um dos pais, retorna TRUE

### Item D

- Montar os vértices e as listas de adjacencia
- Calcular as distâncias de cada vértice do vértice 1.
- Pegar os vértices que retornam da parte C, somar as distâncias de ambos os pais

---
## Membros
- 11796444 - Giovanni Shibaki Camargo 
- 11796451 - Pedro Kenzo Muramatsu Carmo 
