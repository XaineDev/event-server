package dev.xaine.minigame

import dev.xaine.minigame.impl.GameUltraSequencer
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties

class GameContainer {

    val GAME_MAP = HashMap<KClass<out Game>, Game>()
    init {
        addGame(GameUltraSequencer::class)
    }

    private fun addGame(game: KClass<out Game>) {
        val gameInstance = game.createInstance()
        GAME_MAP[gameInstance::class] = gameInstance
    }

    inline fun <reified T: Game> getGame(): T? {
        return GAME_MAP[T::class] as? T
    }
}