data class File(val name: String, val size: Int)

data class Dir(val name: String, val childDir: MutableSet<Dir>, val childFile: MutableSet<File>, var size: Int = -1)

fun main() {
  fun dirSize(dir: Dir): Int {
    if (dir.size != -1) return dir.size
    dir.size = dir.childFile.sumOf { it.size } + dir.childDir.sumOf { dirSize(it) }
    return dir.size
  }

  fun setUpFileSystem(input: List<String>): Dir {
    val root = Dir("root", mutableSetOf(), mutableSetOf())
    val stack = mutableListOf(root)
    var curDir = root
    for (cmd in input) {
      if (cmd.startsWith("$ cd")) {
        when (val dirName = cmd.removePrefix("$ cd ")) {
          "/" -> {
            curDir = root
            stack.clear()
            stack.add(root)
          }

          ".." -> {
            stack.removeLast()
            curDir = stack.last()
          }

          else -> {
            val dir = curDir.childDir.find { it.name == dirName }!!
            curDir = dir
            stack.add(dir)

          }
        }

      } else if (cmd == "$ ls") {
        continue
      } else {
        if (cmd.startsWith("dir ")) {
          val (_, dirName) = cmd.split(" ")
          if (curDir.childDir.find { it.name == dirName } == null)
            curDir.childDir.add(Dir(dirName, mutableSetOf(), mutableSetOf()))
        } else {
          val (fileSize, fileName) = cmd.split(" ")
          curDir.childFile.add(File(fileName, fileSize.toInt()))
        }
      }
    }
    return root
  }

  fun part1(input: List<String>): Int {
    val root = setUpFileSystem(input)
    var sum = 0
    val queue = mutableListOf(root)
    while (queue.isNotEmpty()) {
      val top = queue.removeFirst()
      if (dirSize(top) <= 100000) {
        sum += dirSize(top)
      }
      for (subDir in top.childDir) {
        queue.add(subDir)
      }
    }
    return sum
  }

  fun part2(input: List<String>): Int {
    val root = setUpFileSystem(input)
    val queue = mutableListOf(root)
    val spaceNeeded = 30000000 - (70000000 - dirSize(root))
    val dirSizes = mutableListOf<Int>()
    while (queue.isNotEmpty()) {
      val top = queue.removeFirst()
      dirSizes.add(top.size)
      for (subDir in top.childDir) {
        queue.add(subDir)
      }
    }
    return dirSizes.filter { it >= spaceNeeded }.min()
  }

  val testInput = readInput("Day07_test")
  check(part1(testInput) == 95437)
  check(part2(testInput) == 24933642)

  val input = readInput("Day07")
  println(part1(input))
  println(part2(input))
}
