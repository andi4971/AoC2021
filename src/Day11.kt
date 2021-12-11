
fun main() {
    fun part1(input: List<String>): Int {
        val arr = input.map { line -> line.toCharArray().map { it.digitToInt() }.toMutableList() }.toMutableList()
        var flashes = 0
        for (day in 0 until 100) {
            //increase all by one
            arr.forEachIndexed { row, list ->
                list.forEachIndexed { col, energy ->
                    arr[row][col]++
                }
            }
            var row = 0
            val flashed = mutableListOf<ArrEntry>()
            outer@while (row < arr.size){
                var col = 0
                while (col < arr.first().size) {
                    if(arr[row][col] > 9 && ArrEntry(row, col) !in flashed){
                        flashes++
                        flashed+= ArrEntry(row, col)

                        //update area

                        for(innerRow in row-1..row+1){
                            for(innerCol in col-1..col+1){
                                if(innerRow >= 0 && innerRow < arr.size && innerCol >= 0 && innerCol < arr.first().size){
                                    arr[innerRow][innerCol]++
                                }
                            }
                        }

                        row = 0
                        col = 0
                        continue@outer
                    }
                    col++
                }
                row++
            }
            flashed.forEach {
                arr[it.row] [it.col ] = 0
            }
           /* arr.forEach { println(it) }
            println("--------------------------------")*/
        }

        return flashes
    }

    fun part2(input: List<String>): Int {
        val arr = input.map { line -> line.toCharArray().map { it.digitToInt() }.toMutableList() }.toMutableList()
        var days = 0
        while (true) {
            //increase all by one
            arr.forEachIndexed { row, list ->
                list.forEachIndexed { col, energy ->
                    arr[row][col]++
                }
            }
            var row = 0
            val flashed = mutableListOf<ArrEntry>()
            outer@while (row < arr.size){
                var col = 0
                while (col < arr.first().size) {
                    if(arr[row][col] > 9 && ArrEntry(row, col) !in flashed){
                        flashed+= ArrEntry(row, col)

                        //update area

                        for(innerRow in row-1..row+1){
                            for(innerCol in col-1..col+1){
                                if(innerRow >= 0 && innerRow < arr.size && innerCol >= 0 && innerCol < arr.first().size){
                                    arr[innerRow][innerCol]++
                                }
                            }
                        }

                        row = 0
                        col = 0
                        continue@outer
                    }
                    col++
                }
                row++
            }
            flashed.forEach {
                arr[it.row] [it.col ] = 0
            }
            days++
            if(arr.all { intern -> intern.all { it == 0 } }){
                break;
            }
        }

        return days
    }

    val input = readInput("day11")
    println(part1(input))
    println(part2(input))

}

