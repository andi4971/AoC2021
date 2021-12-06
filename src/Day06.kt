fun main() {
    fun part1(input: List<String>): Int {
        var fishies = input.first().split(",").map { it.toInt() }.toMutableList()
        for (i in 0 until 80) {
            fishies = fishies.map { it - 1 }.toMutableList()
            fishies.filter { it == -1 }.forEach { fishies.add(8) }
            fishies.replaceAll {
                if (it == -1) {
                    6
                } else {
                    it
                }
            }
        }

        return fishies.size
    }

    fun part2(input: List<String>): Long {
        val fishiMap = mutableMapOf<Int, Long>()
        for (i in -1..8) {
            fishiMap[i] = 0
        }
        input.first().split(",").map { it.toInt() }.toMutableList()
            .forEach { fishiMap[it] = fishiMap[it]!! + 1 }
        for(d in 0 until 256){
            for(i in 0..8){
                fishiMap[i-1] = fishiMap[i]!!
            }
            val count = fishiMap[-1]!!
            fishiMap[8]= count
            fishiMap[6] =fishiMap[6]!!+ fishiMap[-1]!!
        }
        return fishiMap.values.sum() - fishiMap[-1]!!
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("day6")

    //check(part1(testInput) == 198)
    //check(part2(input) == 230)

    //val input = readInput("day1")
    println(part1(input))
    println(part2(input))

}
