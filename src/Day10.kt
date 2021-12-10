import java.util.*

fun main() {
    val chunks = mapOf(')' to '(', ']' to '[', '>' to '<', '}' to '{')

    fun part1(input: List<String>): Int {
        val illegal = mutableListOf<Char>()
        input.forEach outer@{ line->
            val stack = Stack<Char>()
            line.forEach {
                if(it in chunks.values){
                    stack.push(it)
                }
                if(it in chunks.keys) {
                    val top = stack.pop()
                    if(top == null){
                        println("empty boi")
                        return@outer
                    }else{
                        if(chunks[it]!! != top){
                            illegal.add(it)
                            return@outer
                        }
                    }
                }
            }
        }

        return illegal.map { when(it) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> 0
        } }.sum()
    }

    fun part2(input: List<String>): Long {
        val incompleteLines = mutableListOf<Stack<Char>>()
        input.forEach outer@{ line->
            val stack = Stack<Char>()
            line.forEach {
                if(it in chunks.values){
                    stack.push(it)
                }
                if(it in chunks.keys) {
                    val top = stack.pop()
                    if(top == null){
                        println("empty boi")
                        return@outer
                    }else{
                        if(chunks[it]!! != top){
                            return@outer
                        }
                    }
                }
            }
            incompleteLines+= stack
        }
        val reversed = chunks.entries.associate{(k,v)-> v to k}
        val erg = incompleteLines.map { stack ->
            var ergString = ""
            while(!stack.empty()){
                ergString+= reversed[stack.pop()]
            }
            ergString
        }
       val mapped = erg.map { line ->
           var score = 0L
           line.forEach {
               score*=5
               score += when(it) {
                   ')' -> 1
                   ']' -> 2
                   '}' -> 3
                   '>' -> 4
                   else -> 0
               }
           }
           score
       }
        return mapped.sorted()[(mapped.size/2)]
    }

    val input = readInput("day10")
    println(part1(input))
    println(part2(input))

}
