fun main() {

  fun slice(str: String): Pair<String, String> = Pair(
    str.slice(0 until str.length / 2),
    str.slice(str.length / 2 until str.length)
  )

  fun priority(item: Char): Int = when {
    item.isLowerCase() -> item.code - 'a'.code + 1
    else -> item.code - 'A'.code + 27
  }

  fun part1(input: List<String>): Int = input.fold(0) { acc, str ->
    val (s1, s2) = slice(str)
    val map = s1.map { it to true }.toMap()
    val item = s2.find { map[it] == true }!!
    acc + priority(item)
  }

  fun part2(input: List<String>): Int =
    input.windowed(3, 3) { it.toList() }
      .fold(0) { acc, strings ->
        val map = mutableMapOf<Char, Int>()
        strings.forEach { str ->
          str.toList().distinct()
            .forEach { map[it] = map.getOrDefault(it, 0) + 1 }
        }
        val ans = map.filterValues { it == 3 }.keys.toList()[0]
        acc + priority(ans)
      }


  val testInput = readInput("Day03_test")
  check(part1(testInput) == 157)
  check(part2(testInput) == 70)

  val input = readInput("Day03")
  println(part1(input))
  println(part2(input))
}
