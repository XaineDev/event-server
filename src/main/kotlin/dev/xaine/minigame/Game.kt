package dev.xaine.minigame

import net.minestom.server.entity.Player

abstract class Game(gameID: String) {

    companion object {
        lateinit var GAME_ID: String
    }

    init {
        GAME_ID = gameID
        // Ensure that GAME_ID is initialized in the child classes
        requireNotNull(GAME_ID) { "GAME_ID must be initialized in the child class." }
    }

    fun getGameID(): String {
        return GAME_ID
    }

    /**
     * returns if game started successfully
     */
    abstract fun start(player: Player, gameData: HashMap<String, String>): Boolean
    abstract fun run(player: Player)
    abstract fun end(player: Player)

}