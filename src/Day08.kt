fun main() {
    fun part1(input: List<String>): Int {
        val numberMap = mutableMapOf<Int, Int>()
        numberMap[2] = 0
        numberMap[3] = 0
        numberMap[4] = 0
        numberMap[7] = 0
        input.forEach { line ->
            line.split(" | ").drop(1).first().split(" ").filter { it.length in listOf(2, 3, 4, 7) }
                .forEach { numberMap[it.length] = numberMap[it.length]!! + 1 }
        }
        return numberMap.values.sum()
    }

    fun part2(input: List<String>): Int {

        var res = 0
        input.forEach { line ->
            val segmentMap = mutableMapOf<Char, Char>()
            val (numbers, output) = line.split(" | ").map { it.split(" ") }.take(2)
            val seven = numbers.first { it.length == 3 }
            val one = numbers.first { it.length == 2 }
            segmentMap['a'] = seven.filterNot { one.contains(it) }.first()

            val potentialBD = numbers.first { it.length == 4 }.filterNot { it in one }
            val five =
                numbers.first { it.length == 5 && potentialBD.all { c -> c in it } && it.contains(segmentMap['a']!!) && it.any { c -> c in one } }

            segmentMap['g'] = five.filterNot { it in potentialBD || it == segmentMap['a']!! || it in one }.first()
            segmentMap['f'] = five.first { it in one }
            segmentMap['c'] = one.first { it != segmentMap['f']!! }

            val three = numbers.first { it.length == 5 && segmentMap.values.all { c -> c in it } }
            segmentMap['d'] = three.first { it !in segmentMap.values }
            val two = numbers.first { it.length == 5 && it != three && it != five }
            segmentMap['e'] =
                two.first { it !in listOf(segmentMap['a'], segmentMap['c'], segmentMap['d'], segmentMap['g']) }
            segmentMap['b'] = numbers.first { it.length == 7 }.first { it !in segmentMap.values }

            var resString = ""
            output.map { num ->
                for(i in 0..9){
                    if(num.toCharArray().sorted() == segmentMap.getSegmentsForNumber(i).sorted()){
                        resString+=i
                    }
                }
            }
            println(resString)
            res+= resString.toInt()
        }

        return res
    }

    val input = readInput("day8")
    println(part1(input))
    println(part2(input))

}

private fun MutableMap<Char, Char>.getSegmentsForNumber(number: Int): List<Char> {
    return when (number) {
        0 -> {
            listOf(this['a'],this['b'],this['c'],this['e'],this['f'],this['g'],)
        }
        1 -> {
            listOf(this['c'], this['f'])
        }
        2 -> {
            listOf(this['a'],this['c'],this['d'],this['e'],this['g'])
        }
        3 -> {
            listOf(this['a'],this['c'],this['d'],this['f'],this['g'],)
        }
        4 -> {
            listOf(this['b'],this['c'],this['d'],this['f'],)
        }
        5 -> {
            listOf(this['a'],this['b'],this['d'],this['f'],this['g'],)
        }
        6-> {
            listOf(this['a'],this['b'],this['d'],this['e'],this['f'],this['g'],)
        } 7 -> {
            listOf(this['a'], this['c'], this['f'],)
        }
        8 -> {
            this.values.toList()
        }
        9 -> {
            this.values.filterNot { it == this['e']!! }
        }
        else -> emptyList()
    }.filterNotNull()
}

