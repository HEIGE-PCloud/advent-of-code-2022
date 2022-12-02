enum class Outcome(val score: Int) {
  Win(6),
  Draw(3),
  Lose(0);
}

enum class RPS(val score: Int) {
  Rock(1),
  Paper(2),
  Scissors(3)
}

fun Move(sMove: String): Move = when (sMove) {
  "A" -> Move(RPS.Rock)
  "B" -> Move(RPS.Paper)
  "C" -> Move(RPS.Scissors)
  "X" -> Move(RPS.Rock)
  "Y" -> Move(RPS.Paper)
  "Z" -> Move(RPS.Scissors)
  else -> throw Exception("Incorrect sMove $sMove")
}

class Move(val move: RPS) {
  val overcome: RPS
    get() = when(move) {
      RPS.Rock -> RPS.Scissors
      RPS.Paper -> RPS.Rock
      RPS.Scissors -> RPS.Paper
    }
  val defeated: RPS
    get() = when(move) {
      RPS.Scissors -> RPS.Rock
      RPS.Rock -> RPS.Paper
      RPS.Paper -> RPS.Scissors
    }

  private fun play(other: Move): Outcome = when (other.move) {
    move -> Outcome.Draw
    overcome -> Outcome.Win
    defeated -> Outcome.Lose
    else -> throw Exception("Impossible to reach here")
  }

  fun playScore(other: Move): Int = move.score + play(other).score
}

fun main() {
  fun part1(input: List<String>): Int =
    input.fold(0) { acc, str ->
      val split = str.split(' ')
      val opponent = Move(split[0])
      val me = Move(split[1])
      acc + me.playScore(opponent)
    }

  fun part2(input: List<String>): Int =
    input.fold(0) { acc, str ->
      val split = str.split(' ')
      val opponent = Move(split[0])
      val me: Move = when (split[1]) {
        "X" -> Move(opponent.overcome)
        "Y" -> Move(opponent.move)
        "Z" -> Move(opponent.defeated)
        else -> throw Exception("Incorrect outcome ${split[1]}")
      }
      acc + me.playScore(opponent)
    }

  val testInput = readInput("Day02_test")
  check(part1(testInput) == 15)
  check(part2(testInput) == 12)

  val input = readInput("Day02")
  println(part1(input))
  println(part2(input))
}
