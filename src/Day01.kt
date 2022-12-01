fun main() {

  fun toCalories(input: List<String>): List<Int> =
    input.flatMapIndexed { index, x ->
      when {
        index == 0 || index == input.lastIndex -> listOf(index)
        x.isEmpty() -> listOf(index - 1, index + 1)
        else -> emptyList()
      }
    }.windowed(size = 2, step = 2) { (from, to) -> input.slice(from..to) }
      .map { it.fold(0) { acc, s -> acc + Integer.parseInt(s) } }

  fun part1(input: List<String>): Int = toCalories(input).max()

  fun part2(input: List<String>): Int =
    toCalories(input).sortedDescending().take(3).sum()

  val testInput = readInput("Day01_test")
  check(part1(testInput) == 24000)
  check(part2(testInput) == 45000)

  val input = readInput("Day01")
  println(part1(input))
  println(part2(input))
}
