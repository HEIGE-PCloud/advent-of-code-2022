fun main() {
  fun isDistinct(string: String) = string.length == string.toList().distinct().size

  fun findPos(line: String, length: Int): Int {
    for (i in 0 until line.length - length) {
      val substr = line.slice(i until i + length)
      if (isDistinct(substr)) return i + length
    }
    return -1
  }

  fun part1(input: List<String>): Int = findPos(input.first(), 4)

  fun part2(input: List<String>): Int = findPos(input.first(), 14)

  val testInput = readInput("Day06_test")
  check(part1(testInput) == 7)
  check(part2(testInput) == 19)

  val input = readInput("Day06")
  println(part1(input))
  println(part2(input))
}
