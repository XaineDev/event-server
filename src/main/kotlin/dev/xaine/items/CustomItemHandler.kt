package dev.xaine.items

import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.PlayerBlockPlaceEvent
import net.minestom.server.event.player.PlayerUseItemEvent
import net.minestom.server.item.ItemStack

class CustomItemHandler {

    private val customItemMap = HashMap<String, CustomItem>()

    fun put(item: CustomItem) {
        customItemMap[item.itemName] = item
    }

    fun get(item: String): CustomItem? {
        return customItemMap[item]
    }

    fun init() {
        val eventHandler = MinecraftServer.getGlobalEventHandler()
        eventHandler.addListener(PlayerUseItemEvent::class.java) {
            val item = it.itemStack
            val customItem = validateCustomItem(item) ?: return@addListener
            customItem.useItem(it.player)
        }
        eventHandler.addListener(PlayerBlockPlaceEvent::class.java) {
            val heldItem = it.player.itemInMainHand
            val customItem = validateCustomItem(heldItem) ?: return@addListener
            it.isCancelled = true
            customItem.useItem(it.player)
        }
    }

    fun validateCustomItem(item: ItemStack): CustomItem? {
        val itemNBT = item.meta().toNBT()["item"] ?: return null
        val itemName = itemNBT.value.toString()
        // println("is custom item")
        val customItemClass : CustomItem = get(itemName) ?: return null
        // println("found custom item")
        return customItemClass
    }

}