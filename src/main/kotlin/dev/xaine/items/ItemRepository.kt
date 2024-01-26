package dev.xaine.items

import dev.xaine.minigame.Game
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class ItemRepository {

    val itemMap: HashMap<KClass<out Item>, Item> = HashMap()
    private val itemIDMap: HashMap<String, KClass<out Item>> = HashMap()

    fun register(item: KClass<out Item>) {
        val itemInstance = item.createInstance()
        itemMap[item] = itemInstance
        itemIDMap[itemInstance.itemID] = item
    }
    inline fun <reified T: Item> get(): T? {
        return itemMap[T::class] as? T
    }

    fun getFromID(itemID: String): Item? {
        return itemMap[itemIDMap[itemID]]
    }
}