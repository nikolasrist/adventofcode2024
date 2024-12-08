package day06

import java.io.File

enum class BoardElements(
    val symbol: Char,
) {
    OBSTACLE('#'),
    EMPTY('.'),
    GUARD_RIGHT('>'),
    GUARD_LEFT('<'),
    GUARD_UP('^'),
    GUARD_DOWN('v'),
}

enum class Direction {
    RIGHT,
    LEFT,
    UP,
    DOWN,
}

enum class StepResult {
    FREE,
    OBSTACLE,
    END_OF_BOARD,
}

data class Position(
    val x: Int,
    val y: Int,
)

fun main() {
    val board = loadBoard("src/day06/Day06Part01_input")
    val startPosition = findStartPosition(board)
    val visitedPositions = mutableSetOf<Position>()
    val lastPosition = moveGuard(board, visitedPositions, startPosition.first, startPosition.second)
    println(
        "Last position: $lastPosition visited ${visitedPositions.size} positions",
    )
}

fun loadBoard(filePath: String): List<List<Char>> {
    val board = mutableListOf<List<Char>>()
    File(filePath).readLines().map { it.toCharArray().toList() }.forEach { board.add(it) }
    return board
}

fun findStartPosition(board: List<List<Char>>): Pair<Direction, Position> {
    var direction = Direction.UP
    var currentPosition = Position(0, 0)
    board.forEachIndexed { index, chars ->
        chars.forEachIndexed { index2, value ->
            when (value) {
                BoardElements.GUARD_RIGHT.symbol -> {
                    currentPosition = Position(index2, index)
                    direction = Direction.RIGHT
                }

                BoardElements.GUARD_LEFT.symbol -> {
                    currentPosition = Position(index2, index)
                    direction = Direction.LEFT
                }

                BoardElements.GUARD_UP.symbol -> {
                    currentPosition = Position(index2, index)
                    direction = Direction.UP
                }

                BoardElements.GUARD_DOWN.symbol -> {
                    currentPosition = Position(index2, index)
                    direction = Direction.DOWN
                }

                else -> {} // nothing to do
            }
        }
    }
    return Pair(direction, currentPosition)
}

fun moveGuard(
    board: List<List<Char>>,
    visited: MutableSet<Position>,
    direction: Direction,
    position: Position,
): Position {
    visited.add(position)
    // check next field
    val nextStep = checkNextStep(board, position, direction)
//    println("Current Position: $position, direction: $direction, next step: $nextStep")

    return when (nextStep) {
        StepResult.FREE -> moveGuard(board, visited, direction, getNextPosition(position, direction))
        StepResult.OBSTACLE ->
            moveGuard(
                board,
                visited,
                rotate(direction),
                getNextPosition(
                    position,
                    rotate(direction),
                ),
            )
        StepResult.END_OF_BOARD -> position
    }
}

fun getNextPosition(
    position: Position,
    direction: Direction,
): Position =
    when (direction) {
        Direction.RIGHT -> Position(position.x + 1, position.y)
        Direction.DOWN -> Position(position.x, position.y + 1)
        Direction.LEFT -> Position(position.x - 1, position.y)
        Direction.UP -> Position(position.x, position.y - 1)
    }

fun rotate(currentDirection: Direction): Direction =
    when (currentDirection) {
        Direction.RIGHT -> Direction.DOWN
        Direction.DOWN -> Direction.LEFT
        Direction.LEFT -> Direction.UP
        Direction.UP -> Direction.RIGHT
    }

fun checkNextStep(
    board: List<List<Char>>,
    position: Position,
    direction: Direction,
): StepResult {
    // change that it automatically rotates and returns false when the board is left.
    when (direction) {
        Direction.RIGHT -> {
            val nextPosition = Position(position.x + 1, position.y)
            if (nextPosition.x >= board[0].size) return StepResult.END_OF_BOARD
            return checkElement(board[nextPosition.y][nextPosition.x])
        }
        Direction.DOWN -> {
            val nextPosition = Position(position.x, position.y + 1)
            if (nextPosition.y >= board.size) return StepResult.END_OF_BOARD
            return checkElement(board[nextPosition.y][nextPosition.x])
        }
        Direction.LEFT -> {
            val nextPosition = Position(position.x - 1, position.y)
            if (nextPosition.x < 0) return StepResult.END_OF_BOARD
            return checkElement(board[nextPosition.y][nextPosition.x])
        }
        Direction.UP -> {
            val nextPosition = Position(position.x, position.y - 1)
            if (nextPosition.y < 0) return StepResult.END_OF_BOARD
            return checkElement(board[nextPosition.y][nextPosition.x])
        }
    }
}

fun checkElement(element: Char): StepResult {
    if (element == BoardElements.OBSTACLE.symbol) return StepResult.OBSTACLE
    return StepResult.FREE
}
