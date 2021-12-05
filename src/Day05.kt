fun main() {
    fun part1(input: List<String>): Int {
        val board = Array(1000) { Array(1000) { 0 } }
        input.map { row -> row.split(" -> ").map { it.split(",").map { x -> x.toInt() } } }
            .map {
                var temp = it.first()
                val p1 = Point(temp[0], temp[1])
                temp = it.drop(1).take(1).flatten()
                val p2 = Point(temp[0], temp[1])
                Vector(p1, p2)
            }.filter { !it.diagonal() }
            .forEach { vec ->
                if(vec.p1.x == vec.p2.x){
                    //vertical
                    val (min, max) = listOf(vec.p1.y, vec.p2.y).sorted()
                    for(i in min..max) {
                        board[i][ vec.p1.x]++;
                    }
                }else {
                    val (min, max) = listOf(vec.p1.x, vec.p2.x).sorted()
                    for(i in min..max) {
                        board[vec.p1.y][i]++;
                    }
                }
            }

        return  board.flatten().count { it >= 2 }
    }

    fun part2(input: List<String>): Int {

        val board = Array(1000) { Array(1000) { 0 } }
        input.map { row -> row.split(" -> ").map { it.split(",").map { x -> x.toInt() } } }
            .map {
                var temp = it.first()
                val p1 = Point(temp[0], temp[1])
                temp = it.drop(1).take(1).flatten()
                val p2 = Point(temp[0], temp[1])
                Vector(p1, p2)
            }
            .forEach { vec ->
                if(vec.diagonal()) {
                    if(vec.p1.x < vec.p2.x){
                        if(vec.p1.y < vec.p2.y) {
                            var yCount = vec.p1.y
                            for(i in vec.p1.x..vec.p2.x){
                                board[yCount][i]++
                                yCount++
                            }
                        }else{
                            var yCount = vec.p1.y
                            for(i in vec.p1.x..vec.p2.x){
                                board[yCount][i]++
                                yCount--
                            }
                        }
                    }else {

                        if(vec.p2.y < vec.p1.y) {
                            var yCount = vec.p2.y
                            for(i in vec.p2.x..vec.p1.x){
                                board[yCount][i]++
                                yCount++
                            }
                        }else{
                            var yCount = vec.p2.y
                            for(i in vec.p2.x..vec.p1.x){
                                board[yCount][i]++
                                yCount--
                            }
                        }
                    }
                }
                else {
                    if(vec.p1.x == vec.p2.x){
                        val (min, max) = listOf(vec.p1.y, vec.p2.y).sorted()
                        for(i in min..max) {
                            board[i][ vec.p1.x]++;
                        }
                    }else {
                        val (min, max) = listOf(vec.p1.x, vec.p2.x).sorted()
                        for(i in min..max) {
                            board[vec.p1.y][i]++;
                        }
                    }
                }

            }
        return  board.flatten().count { it >= 2 }
    }

    // test if implementation meets criteria from the description, like:
    val input = readInput("day5")

    //check(part1(testInput) == 198)
    //check(part2(input) == 230)

    //val input = readInput("day1")
    println(part1(input))
    println(part2(input))
}

data class Point(val x: Int, val y: Int)
data class Vector(val p1: Point, val p2: Point)

fun Vector.diagonal(): Boolean {
    return this.p1.x != this.p2.x && this.p1.y != this.p2.y
}
