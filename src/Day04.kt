import kotlin.math.max
import kotlin.math.min

typealias Range = Pair<Int, Int>

fun main() {
  fun parseRange(str: String): Pair<Range, Range> {
    val (strRange1, strRange2) = str.split(',')
    val (s1, e1) = strRange1.split('-')
    val (s2, e2) = strRange2.split('-')
    return Pair(Range(s1.toInt(), e1.toInt()), Range(s2.toInt(), e2.toInt()))
  }

  fun contains(range1: Range, range2: Range): Boolean =
    range1.first <= range2.first && range1.second >= range2.second ||
      range2.first <= range1.first && range2.second >= range1.second

  fun overlaps(range1: Range, range2: Range): Boolean =
    max(range1.first, range2.first) <= min(range1.second, range2.second)

  fun part1(input: List<String>): Int =
    input.fold(0) { acc, str ->
      val (range1, range2) = parseRange(str)
      when (contains(range1, range2)) {
        true -> acc + 1
        false -> acc
      }
    }

  fun part2(input: List<String>): Int =
    input.fold(0) { acc, str ->
      val (range1, range2) = parseRange(str)
      when (overlaps(range1, range2)) {
        true -> acc + 1
        false -> acc
      }
    }

  val testInput = readInput("Day04_test")
  check(part1(testInput) == 2)
  check(part2(testInput) == 4)

  val input = readInput("Day04")
  println(part1(input))
  println(part2(input))
}
