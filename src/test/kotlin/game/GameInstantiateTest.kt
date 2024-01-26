package game

import dev.xaine.minigame.GameContainer
import dev.xaine.minigame.impl.GameUltraSequencer
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.Test

class GameInstantiateTest {

    @Test
    fun testFromClass() {
        val gameContainer = GameContainer()
        gameContainer.register(GameUltraSequencer::class)

        val ultraSequencer = gameContainer.getGame<GameUltraSequencer>()
        assert(ultraSequencer != null)
        val gameID = ultraSequencer?.getGameID()
        assert(!gameID.isNullOrEmpty())
    }

    @Test
    fun testFromID() {
        val gameContainer = GameContainer()
        gameContainer.register(GameUltraSequencer::class)

        val ultraSequencer = gameContainer.getGameFromID("ultrasequencer")
        assert(ultraSequencer != null)
        val gameID = ultraSequencer?.getGameID()
        assert(!gameID.isNullOrEmpty())
    }

    @Test
    fun benchmarkTest() {
        val gameContainer = GameContainer()
        gameContainer.register(GameUltraSequencer::class)

        val testLength = 1000
        val tests = 100
        val testData = Array<Long>(tests){0}
        for (test in 0..<tests) {
            for (index in 0..<testLength) {
                val initTime = System.nanoTime();

                run {
                    val ultraSequencer = gameContainer.getGame<GameUltraSequencer>()
                    assert(ultraSequencer != null)
                }

                val endTime = System.nanoTime()
                testData[test] = endTime-initTime
            }
        }
        println("Average Time: ${average(testData)}")
    }

    private fun average(input: Array<Long>): Long {
        var total = 0L
        for (value in input) {
            total += value
        }
        total /= input.size
        return total
    }

    private fun log(message: String) {
        println("[GameInstantiateTest] $message")
    }
}