fun main() {
    fun part1(input: List<String>): Int {

        val intRows = input.map { s -> s.toCharArray().map { it.digitToInt() } }
        var gammaString = ""
        for (i in intRows.first().indices) {
            var zero = intRows.map { it[i] }.filter { it == 0 }.count()
            var one = intRows.map { it[i] }.filter { it == 1 }.count()
            if (one > zero) {
                gammaString += "1"
            } else {
                gammaString += "0"
            }
        }
        val gamma = gammaString.toInt(2)
        val inverse = gamma xor gammaString.map { "1" }.joinToString(separator = "").toInt(2)
        return gamma * inverse

    }

    fun part2(input: List<String>): Int {
        val intRows = input.map { s -> s.toCharArray().map { it.digitToInt() } }
        val oxygenNumbers = intRows.toMutableList()
        val coNumbers = intRows.toMutableList()
        for (i in intRows.first().indices) {
            val oxygenZero = oxygenNumbers.map { it[i] }.filter { it == 0 }.count()
            val oxygenOne = oxygenNumbers.map { it[i] }.filter { it == 1 }.count()
            val coZero = coNumbers.map { it[i] }.filter { it == 0 }.count()
            val coOne = coNumbers.map { it[i] }.filter { it == 1 }.count()

            if (oxygenOne >= oxygenZero) {
                oxygenNumbers.removeIf { it[i] == 0 && oxygenNumbers.size > 1}
            } else {
                oxygenNumbers.removeIf { it[i] == 1 && oxygenNumbers.size > 1 }
            }
            if (coOne >= coZero) {
                coNumbers.removeIf { it[i] == 1 && coNumbers.size > 1}
            } else {
                coNumbers.removeIf { it[i] == 0 && coNumbers.size > 1 }
            }
        }

        return oxygenNumbers.first().joinToString(separator = "").toInt(2) * coNumbers.first().joinToString(separator = "").toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("day3")

    //check(part1(testInput) == 198)
    //check(part2(input) == 230)

    //val input = readInput("day1")
    println(part1(input))
    println(part2(input))
}
