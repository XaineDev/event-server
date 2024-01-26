package dev.xaine.menu.impl.menu

import dev.xaine.menu.CustomMenu
import dev.xaine.menu.MenuItems
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType

class SkyblockMenu : CustomMenu {
    override var menuInventory: Inventory? = null

    override fun createMenu(): Inventory {
        val baseInventory = Inventory(InventoryType.CHEST_6_ROW, "SkyBlock Menu")
        borderInventory(baseInventory, MenuItems.GRAY_PANE)
        addCloseButton(baseInventory)
        menuInventory = baseInventory
        return baseInventory
    }
}