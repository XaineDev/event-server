package dev.xaine.minigame.impl

import dev.xaine.minigame.Game
import net.minestom.server.entity.Player

class GameUltraSequencer : Game("ultrasequencer") {

    override fun start(player: Player, gameData: HashMap<String, String>): Boolean {
        return false;
    }

    override fun run(player: Player) {
    }

    override fun end(player: Player) {
    }

    class Grid(width: Int, height: Int) {
        val gridSize = intArrayOf(width, height)
    }
    data class ClickReward(val clicks: Int, val reward: Int)
    enum class Difficulty(name: String, gridSize: Grid, xpReward: Int, clickRewards: Array<ClickReward>, xpRequirement: Int) {
        SUPREME(
            "Supreme",
            Grid(7, 3),
            3500,
            arrayOf(
                ClickReward(2, 1),
                ClickReward(4, 2)
            ),
            25
        ),
        TRANSCENDENT(
            "Transcendent",
            Grid(7, 4),
            5000,
            arrayOf(
                ClickReward(2, 1),
                ClickReward(4, 2)
            ),
            30
        ),
        METAPHYSICAL(
            "Metaphysical",
            Grid(9, 4),
            7000,
            arrayOf(
                ClickReward(2, 1),
                ClickReward(4, 2),
                ClickReward(6, 3)
            ),
            40
        );



        companion object {
            val VALUES = entries.toTypedArray()
        }
    }
}