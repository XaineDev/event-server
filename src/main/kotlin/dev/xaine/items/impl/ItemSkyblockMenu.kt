package dev.xaine.items.impl

import dev.xaine.items.CustomItem
import dev.xaine.items.ItemCreator
import dev.xaine.menu.impl.menu.SkyblockMenu
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class ItemSkyblockMenu : CustomItem("SKYBLOCK_MENU", false) {

    private val skyblockMenuItem = ItemCreator.createItemStack(
        material = Material.NETHER_STAR,
        name = Component.text("Skyblock Menu").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false)
            .append(Component.text(" (Click)").color(NamedTextColor.GRAY)),
        itemID = this.itemName
    )
        .lore(
            *ItemCreator.loreToComponents("View all of your SkyBlock progress, including all of your Skills, Collections, Recipies, and more "),
            Component.text("").decoration(TextDecoration.ITALIC, false),
            Component.text("Click to open!", NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)
        ).build()
    override val itemEvents: EventNode<Event>
        get() = EventNode.all(itemName)

    override fun getItemStack(): ItemStack {
        return skyblockMenuItem
    }

    override fun useItem(player: Player) {
        openGUI(player)
    }

    override fun movedItem(player: Player) {
        openGUI(player)
    }

    private fun openGUI(player: Player) {
        val menu = SkyblockMenu()
        menu.createMenu()
        menu.showMenu(player)
    }
}