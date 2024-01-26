package game

import dev.xaine.minigame.GameContainer
import dev.xaine.minigame.impl.GameUltraSequencer
import kotlin.test.Test

class GameInstantiateTest {
    @Test
    fun test() {
        log("running test")
        val gameContainer = GameContainer()
        log("created game container")
        val ultraSequencer = gameContainer.getGame<GameUltraSequencer>()
        log("fetched ultra sequencer game")
        assert(ultraSequencer != null)
        log("asserted game is not null")
        val gameID = ultraSequencer?.getGameID()
        log("found game ${ultraSequencer!!::class} with id $gameID")
    }

    private fun log(message: String) {
        println("[GameInstantiateTest] $message")
    }
}