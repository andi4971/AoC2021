import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
       val crabs = input.first().split(",").map { it.toInt() }
       var minFuel = Int.MAX_VALUE
        crabs.forEach { crab ->
            var fuel = 0
            crabs.forEach { dest ->
                fuel+= abs(crab -dest)
            }
            if (fuel < minFuel) minFuel = fuel
        }
        return minFuel
    }

    fun part2(input: List<String>): Int {
        val crabs = input.first().split(",").map { it.toInt() }
        val range = IntRange(crabs.minOf { it }, crabs.maxOf { it })
        var minFuel = Int.MAX_VALUE
        range.forEach { crab ->
            var fuel = 0
            crabs.forEach { dest ->
                val dist = abs(crab -dest)
                    fuel+= (dist*dist + dist)/2
            }
            if (fuel < minFuel) minFuel = fuel
        }
        return minFuel
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("day7")

    //check(part1(testInput) == 198)
    //check(part2(input) == 230)

    //val input = readInput("day1")
    println(part1(input))
    println(part2(input))

}
