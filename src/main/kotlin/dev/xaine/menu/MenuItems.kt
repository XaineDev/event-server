package dev.xaine.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.item.Material

object MenuItems {
    val GRAY_PANE = MenuItemCreator.createItemStack(Material.GRAY_STAINED_GLASS_PANE, Component.text("")).build()
    var CLOSE_MENU = MenuItemCreator.createItemStack(Material.BARRIER, Component.text("Close").color(NamedTextColor.RED)).build()
}