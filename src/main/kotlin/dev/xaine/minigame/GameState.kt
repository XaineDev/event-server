package dev.xaine.minigame

import net.minestom.server.entity.Player

class GameState(runningGame: Game, player: Player) {

    private val gameBase: Game = runningGame
    private val gamePlayer: Player = player
    private var runningState: RunningState = RunningState.NONE

    enum class RunningState {
        STARTING,
        RUNNING,
        ENDING,
        NONE;
    }
}