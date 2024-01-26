package dev.xaine.menu

import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack

interface CustomMenu {

    var menuInventory: Inventory?
    fun createMenu(): Inventory

    fun showMenu(player: Player) {
        menuInventory ?: return
        player.openInventory(menuInventory!!)
    }

    fun fillInventory(inventory: Inventory, item: ItemStack) {
        for(slot in 0..<inventory.size)
            inventory.setItemStack(slot, item)
    }

    fun borderInventory(inventory: Inventory, item: ItemStack) {
        if (!validInventories.contains(inventory.inventoryType)) return
        val inventorySize = inventory.size
        val inventoryRows = inventorySize / 9
        if (inventoryRows <= 2) { // no need to border the gui as there are one or fewer lines
            fillInventory(inventory, item)
            return
        }

        for(slot in 0..<9) // top row
            inventory.setItemStack(slot, item)

        for(row in 1..<inventoryRows) { // all rows apart from top or bottom
            val rowMinSlot = row*9
            inventory.setItemStack(rowMinSlot, item)
            inventory.setItemStack(rowMinSlot+8, item)
        }

        for(slot in inventorySize-9..<inventorySize) // bottom row
            inventory.setItemStack(slot, item)
    }

    fun addCloseButton(inventory: Inventory) {
        inventory.setItemStack(inventory.size-4, MenuItems.CLOSE_MENU)
    }

    companion object {
        val validInventories: Array<InventoryType> = arrayOf(
            InventoryType.CHEST_1_ROW,
            InventoryType.CHEST_2_ROW,
            InventoryType.CHEST_3_ROW,
            InventoryType.CHEST_4_ROW,
            InventoryType.CHEST_5_ROW,
            InventoryType.CHEST_6_ROW,
            InventoryType.SHULKER_BOX
        )
    }
}