fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        val arr = input.map { it.toCharArray().map { c -> c.digitToInt() } }
        arr.forEachIndexed { row, list ->
            list.forEachIndexed { col, num ->
                    var lowPoint = true
                    //up
                    if (row - 1 >= 0) {
                        if (arr[row - 1][col] <= num) {
                            lowPoint = false
                        }
                    }
                    //down
                    if (row + 1 < arr.size) {
                        if (arr[row + 1][col] <= num) {
                            lowPoint = false
                        }
                    }
                    //left
                    if (col - 1 >= 0) {
                        if (arr[row][col - 1] <= num) {
                            lowPoint = false
                        }
                    }
                    //right
                    if (col + 1 < arr[row].size) {
                        if (arr[row][col + 1] <= num) {
                            lowPoint = false
                        }
                    }
                    if (lowPoint) {
                        sum += num + 1
                    }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val arr = input.map { it.toCharArray().map { c -> c.digitToInt() } }
        val lowPoints = mutableListOf<ArrEntry>()
        val basinSizes = mutableListOf<Int>()
        arr.forEachIndexed { row, list ->
            list.forEachIndexed { col, num ->
                var lowPoint = true
                //up
                if (row - 1 >= 0) {
                    if (arr[row - 1][col] <= num) {
                        lowPoint = false
                    }
                }
                //down
                if (row + 1 < arr.size) {
                    if (arr[row + 1][col] <= num) {
                        lowPoint = false
                    }
                }
                //left
                if (col - 1 >= 0) {
                    if (arr[row][col - 1] <= num) {
                        lowPoint = false
                    }
                }
                //right
                if (col + 1 < arr[row].size) {
                    if (arr[row][col + 1] <= num) {
                        lowPoint = false
                    }
                }
                if (lowPoint) {
                    lowPoints.add(ArrEntry(row, col ))
                }
            }
        }

        lowPoints.forEach { point ->
            val size = calcBasinSize(point, arr, mutableListOf()).size +1
            basinSizes.add(size)
        }

        return basinSizes.sorted().takeLast(3).reduce { acc, i ->  acc * i }
    }

    val input = readInput("day9")
    println(part1(input))
    println(part2(input))

}

fun calcBasinSize(curr: ArrEntry, arr: List<List<Int>>, visitedPoints: MutableList<ArrEntry>): List<ArrEntry> {
    val basinPoints = mutableListOf<ArrEntry>()
    val pointsToVisit = mutableListOf<ArrEntry>()
    if (curr.row - 1 >= 0) {
        val pos = ArrEntry(curr.row-1, curr.col)
        pointsToVisit.add(pos)
    }
    //down
    if (curr.row + 1 < arr.size) {
        val pos = ArrEntry(curr.row+1, curr.col)
        pointsToVisit.add(pos)

    }
    //left
    if (curr.col - 1 >= 0) {
        val pos = ArrEntry(curr.row, curr.col-1)
        pointsToVisit.add(pos)

    }
    //right
    if (curr.col + 1 < arr[curr.row].size) {
        val pos = ArrEntry(curr.row, curr.col+1)
        pointsToVisit.add(pos)
    }
    pointsToVisit.removeIf {
        arr[curr] > arr[it] || arr[it] == 9
    }
    basinPoints += (pointsToVisit - visitedPoints.toSet()).map {
        val temp = visitedPoints.distinct()
        visitedPoints.clear()
        visitedPoints += temp + pointsToVisit
        calcBasinSize(it, arr, visitedPoints)
    }.flatten()
    basinPoints +=pointsToVisit
    return basinPoints.toSet().toList()
}
data class ArrEntry(val row: Int, val col: Int)

operator fun List<List<Int>>.get(arrEntry: ArrEntry) = this[arrEntry.row][arrEntry.col]
