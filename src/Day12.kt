import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
import java.util.*

fun main() {
    fun part1(input: List<String>): Int {
        val graph = SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge::class.java)
        input.forEach { line ->
            val (left, right) = line.split("-", limit = 2)
            if (!graph.vertexSet().contains(left)) {
                graph.addVertex(left)
            }
            if (!graph.vertexSet().contains(right)) {
                graph.addVertex(right)
            }
            graph.addEdge(left, right)
            graph.addEdge(right, left)
        }
        val nodesToRemove = mutableListOf<String>()
        graph.vertexSet().forEach { v ->
            if (graph.edgesOf(v).all {
                    graph.getEdgeTarget(it)[0].isLowerCase() && graph.getEdgeTarget(it) !in listOf(
                        "start",
                        "end"
                    )
                }) {
                nodesToRemove.add(v)
            }
        }
        /*nodesToRemove.forEach {
            graph.removeVertex(it)
        }*/
        var temp = graph.incomingEdgesOf("start").toMutableList()
        temp.forEach {
            graph.removeEdge(it)
        }
        temp = graph.outgoingEdgesOf("end").toMutableList()
        temp.forEach {
            graph.removeEdge(it)
        }
        var smallTwice: String? = null
        fun getCount(curr: String, alreadyVisited: MutableList<String>): Int {
            if (curr == "end") {
                return 1
            }
            if (curr[0].isLowerCase() && curr in alreadyVisited && smallTwice != null) {
                return 0
            } else if (curr[0].isLowerCase() && curr in alreadyVisited) {
                smallTwice = curr
            }
            val outgoingNodes = graph.outgoingEdgesOf(curr).map { graph.getEdgeTarget(it) }
            alreadyVisited.add(curr)
            val sum = outgoingNodes.sumOf {
                getCount(it, alreadyVisited)
            }
            alreadyVisited.remove(curr)
            if (smallTwice == curr) smallTwice = null

            return sum
        }


        return getCount("start", mutableListOf())
    }


    fun part2(input: List<String>): Int {


        return 0
    }

    val input = readInput("day12")
    println(part1(input))
    println(part2(input))

}

