package dev.xaine.minigame

import dev.xaine.minigame.impl.GameUltraSequencer
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties

class GameContainer {

    val GAME_MAP = HashMap<KClass<out Game>, Game>()
    val ID_MAP = HashMap<String, KClass<out Game>>()
    init {
        addGame(GameUltraSequencer::class)
    }

    private fun addGame(gameClass: KClass<out Game>) {
        val gameInstance = gameClass.createInstance()
        GAME_MAP[gameClass] = gameInstance
        ID_MAP[gameInstance.getGameID()] = gameClass
    }

    inline fun <reified T: Game> getGame(): T? {
        return GAME_MAP[T::class] as? T
    }

    fun getGameFromID(gameID: String): Game? {
        return GAME_MAP[ID_MAP[gameID]]
    }
}