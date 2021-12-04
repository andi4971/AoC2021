fun main() {
    fun part1(input: List<String>): Int {
        val drawnNumbers = input.first().split(",").map { it.toInt() }
        val boards = input.drop(2)
            .filter { it.isNotBlank() }
            .map { row -> row.trim().split(" ").filter { it.isNotBlank() }.map { it.toInt() to false }.toMutableList() }
            .chunked(5)
        drawnNumbers.forEachIndexed { currIdx, curr ->
            boards.forEach { board ->
                board.forEach { row ->
                    row.forEachIndexed { i, it->
                        if(it.first == curr) {
                            row[i] = it.copy(second = true)
                        }
                    }

                }
            }
            fun List<List<Pair<Int, Boolean>>>.getUnmarkedEntries(): List<Int> {
                return this.flatten().filter { !it.second }.map { it.first }
            }
            boards.forEach { board ->
                board.forEach { row ->
                    row.forEachIndexed { i, it->
                        if(it.first == curr) {
                            row[i] = it.copy(second = true)
                        }
                    }

                }
            }
            boards.forEach { board ->
                val foundHorizontal = board.any{ row ->
                    row.all { it.second }
                }
                var foundVertical = false
                for(i in board.indices){
                    val vert = mutableListOf<Pair<Int, Boolean>>()
                    for(j in board.indices) {
                        vert.add(board[i][j])
                    }
                    if(vert.all { it.second }){
                        foundVertical = true
                    }

                }
                if(foundHorizontal || foundVertical) {
                    return board.getUnmarkedEntries().sum() * curr
                }
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val drawnNumbers = input.first().split(",").map { it.toInt() }
        var winning = 0
        val boards = input.drop(2)
            .filter { it.isNotBlank() }
            .map { row -> row.trim().split(" ").filter { it.isNotBlank() }.map { it.toInt() to false }.toMutableList() }
            .chunked(5)
            .map { it to false }
            .toMutableList()
        drawnNumbers.forEach {  curr ->
            boards.forEach { board ->
                board.first.forEach { row ->
                    row.forEachIndexed { i, it->
                        if(it.first == curr) {
                            row[i] = it.copy(second = true)
                        }
                    }

                }
            }

            boards.forEach { board ->
                board.first.forEach { row ->
                    row.forEachIndexed { i, it->
                        if(it.first == curr) {
                            row[i] = it.copy(second = true)
                        }
                    }

                }
            }
            boards.forEachIndexed { bidx, board ->
                val foundHorizontal = board.first.any{ row ->
                    row.all { it.second }
                }
                var foundVertical = false
                for(i in board.first.indices){
                    val vert = mutableListOf<Pair<Int, Boolean>>()
                    for(j in board.first.indices) {
                        vert.add(board.first[j][i])
                    }
                    if(vert.all { it.second }){
                        foundVertical = true
                    }

                }
                if(foundHorizontal || foundVertical) {
                    if(!board.second){
                        boards[bidx] = board.copy(second = true)
                        winning = board.first.getUnmarkedEntries().sum() * curr
                    }
                }
            }
        }
        return winning
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("day4")

    //check(part1(testInput) == 198)
    //check(part2(input) == 230)

    //val input = readInput("day1")
    println(part1(input))
    println(part2(input))
}

fun List<List<Pair<Int, Boolean>>>.getUnmarkedEntries(): List<Int> {
    return this.flatten().filter { !it.second }.map { it.first }
}
