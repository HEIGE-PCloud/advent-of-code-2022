fun main() {
  /**
   * Convert the list of input to a list of calories
   */
  fun toCalories(input: List<String>): List<Int> =
    input
      .fold(
        Pair<List<List<String>>, List<String>>(
          emptyList(),
          emptyList()
        )
      ) { (acc, cur), str ->
        when {
          str == input.last() -> Pair(
            acc.plusElement(cur.plus(str)),
            emptyList()
          )
          str.isBlank() -> Pair(acc.plusElement(cur), emptyList())
          else -> Pair(acc, cur.plus(str))
        }
      }
      .first
      .map {
        it.fold(0) { acc, s ->
          acc + Integer.parseInt(s)
        }
      }

  fun part1(input: List<String>): Int =
    toCalories(input).max()

  fun part2(input: List<String>): Int =
    toCalories(input).sortedDescending().take(3).sum()

  val testInput = readInput("Day01_test")
  check(part1(testInput) == 24000)
  check(part2(testInput) == 45000)

  val input = readInput("Day01")
  println(part1(input))
  println(part2(input))
}
