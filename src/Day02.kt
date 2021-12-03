fun main() {
    fun part1(input: List<String>): Int {

        val fields = input.map { it.split(" ") }
            .groupBy { it[0] }
            .mapValues { kv -> kv.value.map { it.drop(1) }.flatten().map { it.toInt() }.sum() }
        return fields["forward"]!! * (fields["down"]!! - fields["up"]!!)


    }

    fun part2(input: List<String>): Int {
        var aim = 0
        var horzional = 0
        var depth = 0
        input.map { it.split(" ", limit = 2) }
            .map { it[0] to it[1].toInt() }
            .forEach {
                when (it.first) {
                    "forward" -> {
                        horzional += it.second
                        depth+= it.second*aim
                    }
                    "down" -> {
                        aim+= it.second
                    }
                    "up" -> {
                        aim-=it.second
                    }
                }
            }
        return horzional * depth

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day1")
    //check(part1(testInput) == 150)
    //check(part2(testInput) == 900)

    val input = readInput("day1")
    println(part1(input))
    println(part2(input))
}
