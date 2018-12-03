@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson5

import java.util.*

/**
 * Эйлеров цикл.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
 * Если в графе нет Эйлеровых циклов, вернуть пустой список.
 * Соседние дуги в списке-результате должны быть инцидентны друг другу,
 * а первая дуга в списке инцидентна последней.
 * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
 * Веса дуг никак не учитываются.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
 *
 * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
 * связного графа ровно по одному разу
 *
 * Трудоемкость Т = О(n+m), где n - кол-во вершин, m - кол-во ребер
 * Ресурсоемкость R = О(n+m)
 */
fun Graph.findEulerLoop(): List<Graph.Edge> {
    if (!checkForEulerLoop()) return emptyList()
    val path: Deque<Graph.Vertex> = LinkedList()
    for (vertex in vertices) {
        path.addFirst(vertex)
        break
    }
    val edges = edges
    while (path.size < getEdges().size) {
        val currentVertex = path.last()
        var visited = true
        for (edge in getConnections(currentVertex).values) {
            if (edge in edges) {
                visited = false
                when (currentVertex) {
                    edge.begin -> {
                        path.addLast(edge.end)
                        edges.remove(edge)
                    }
                    edge.end -> {
                        path.addLast(edge.begin)
                        edges.remove(edge)
                    }
                }
                break
            }
        }
        if (visited) {
            if (path.last != path.first) path.addFirst(path.last)
            path.removeLast()
        }
    }
    path.addLast(path.first)
    val result = mutableListOf<Graph.Edge>()
    while (path.size > 1) {
        val firstVertex = path.first
        val connections = getConnections(firstVertex).values
        path.removeFirst()
        val secondVertex = path.first
        val edge = connections.find { it.begin == secondVertex || it.end == secondVertex }
        edge?.let { result.add(it) }
    }
    return result
}

fun Graph.checkForEulerLoop(): Boolean {
    var oddVertex = 0
    for (vertex in vertices) if (getConnections(vertex).size % 2 == 1) oddVertex++
    if (oddVertex > 2) return false

    val visited = mutableMapOf<Graph.Vertex, Boolean>()
    for (vertex in vertices) visited[vertex] = false
    for (vertex in vertices) {
        if (getConnections(vertex).isNotEmpty())
            dfs(vertex, visited)
        break
    }
    for (vertex in vertices)
        if (getConnections(vertex).isNotEmpty() && visited[vertex] == false) return false
    return true
}

fun Graph.dfs(start: Graph.Vertex, visited: MutableMap<Graph.Vertex, Boolean>): Int {
    var visitedVertices = 1
    visited[start] = true
    for (edge in getConnections(start).values) {
        when (start) {
            edge.begin ->
                if (visited[edge.end] == false) visitedVertices += dfs(edge.end, visited)
            edge.end ->
                if (visited[edge.begin] == false) visitedVertices += dfs(edge.begin, visited)
        }
    }
    return visitedVertices
}

/**
 * Минимальное остовное дерево.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему минимальное остовное дерево.
 * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
 * вернуть любое из них. Веса дуг не учитывать.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ:
 *
 *      G    H
 *      |    |
 * A -- B -- C -- D
 * |    |    |
 * E    F    I
 * |
 * J ------------ K
 */
fun Graph.minimumSpanningTree(): Graph {
    TODO()
}

/**
 * Максимальное независимое множество вершин в графе без циклов.
 * Сложная
 *
 * Дан граф без циклов (получатель), например
 *
 *      G -- H -- J
 *      |
 * A -- B -- D
 * |         |
 * C -- F    I
 * |
 * E
 *
 * Найти в нём самое большое независимое множество вершин и вернуть его.
 * Никакая пара вершин в независимом множестве не должна быть связана ребром.
 *
 * Если самых больших множеств несколько, приоритет имеет то из них,
 * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
 *
 * В данном случае ответ (A, E, F, D, G, J)
 *
 * Эта задача может быть зачтена за пятый и шестой урок одновременно
 */
fun Graph.largestIndependentVertexSet(): Set<Graph.Vertex> {
    TODO()
}

/**
 * Наидлиннейший простой путь.
 * Сложная
 *
 * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
 * Простым считается путь, вершины в котором не повторяются.
 * Если таких путей несколько, вернуть любой из них.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ: A, E, J, K, D, C, H, G, B, F, I
 */
fun Graph.longestSimplePath(): Path {
    TODO()
}