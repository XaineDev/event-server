package dev.xaine.items.impl

import dev.xaine.items.CustomItem
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.attribute.Attribute
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.PlayerUseItemEvent
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class ItemDecrementSpeed : CustomItem("DECREMENT_SPEED") {

    override val itemEvents: EventNode<Event>
        get() = EventNode.all(itemName)

    override fun getItemStack(): ItemStack {
        val itemStack = ItemStack.builder(Material.REDSTONE)
            .meta(defaultNBT)
            .displayName(Component.text("Speed Decrement", NamedTextColor.GRAY, TextDecoration.BOLD))
            .lore(
                Component.text("RIGHT CLICK", TextColor.color(0xeb9234), TextDecoration.BOLD),
                Component.text("Decrements Your Speed", NamedTextColor.GRAY)
            )
            .build()
        return itemStack
    }

    override fun useItem(player: Player) {
        player.sendMessage(Component.text("Consumed Speed Decrement"))
        player.getAttribute(Attribute.MOVEMENT_SPEED).baseValue -= 0.02f
        player.sendMessage(Component.text("new speed ${player.getAttribute(Attribute.MOVEMENT_SPEED).baseValue}"))
    }
}