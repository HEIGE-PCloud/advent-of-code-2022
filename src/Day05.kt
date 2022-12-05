typealias Stack = MutableList<Char>

fun main() {
  val testList = listOf(
    mutableListOf(),
    mutableListOf('N', 'Z'),
    mutableListOf('D', 'C', 'M'),
    mutableListOf('P'),
  )
  val list = listOf<Stack>(
    mutableListOf(),
    mutableListOf('P', 'V', 'Z', 'W', 'D', 'T'),
    mutableListOf('D', 'J', 'F', 'V', 'W', 'S', 'L'),
    mutableListOf('H', 'B', 'T', 'V', 'S', 'L', 'M', 'Z'),
    mutableListOf('J', 'S', 'R'),
    mutableListOf('W', 'L', 'M', 'F', 'G', 'B', 'Z', 'C'),
    mutableListOf('B', 'G', 'R', 'Z', 'H', 'V', 'W', 'Q'),
    mutableListOf('N', 'D', 'B', 'C', 'P', 'J', 'V'),
    mutableListOf('Q', 'B', 'T', 'P'),
    mutableListOf('C', 'R', 'Z', 'G', 'H'),
  )

  fun parseMove(moveStr: String): Triple<Int, Int, Int> {
    val commandStr = moveStr.removePrefix("move ")
    val (numStr, commandSubStr) = commandStr.split(" from ")
    val (fromStr, toStr) = commandSubStr.split(" to ")
    return Triple(
      Integer.parseInt(numStr),
      Integer.parseInt(fromStr),
      Integer.parseInt(toStr)
    )
  }

  fun move(moveStr: String, list: List<Stack>) {
    val (num, from, to) = parseMove(moveStr)
    for (i in 1..num) {
      val item = list[from].removeFirst()
      list[to].add(0, item)
    }
  }

  fun move2(moveStr: String, list: List<Stack>) {
    val (num, from, to) = parseMove(moveStr)
    val tempStack = mutableListOf<Char>()
    for (i in 1..num) {
      tempStack.add(0, list[from].removeFirst())
    }
    for (i in 1..num) {
      list[to].add(0, tempStack.removeFirst())
    }
  }

  fun part1(input: List<String>, list: List<Stack>): String {
    val (moves, _) = input.partition { it.startsWith("move") }
    moves.forEach { move(it, list) }
    return list.fold("") { ans, stack ->
      if (stack.size > 0) {
        ans + stack[0]
      } else {
        ans
      }
    }
  }

  fun part2(input: List<String>, list: List<Stack>): String {
    val (moves, _) = input.partition { it.startsWith("move") }
    moves.forEach { move2(it, list) }
    return list.fold("") { ans, stack ->
      if (stack.size > 0) {
        ans + stack[0]
      } else {
        ans
      }
    }
  }

  val testInput = readInput("Day05_test")
//  check(part1(testInput, testList) == "CMZ")
  check(part2(testInput, testList) == "MCD")

  val input = readInput("Day05")
//  println(part1(input, list))
  println(part2(input, list))
}
