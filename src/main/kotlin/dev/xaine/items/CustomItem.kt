package dev.xaine.items

import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.item.ItemStack
import org.jglrxavpok.hephaistos.nbt.NBT
import org.jglrxavpok.hephaistos.nbt.NBTCompound

abstract class CustomItem(val itemName: String, val moveable: Boolean = true) {
    protected abstract val itemEvents: EventNode<Event>
    protected val defaultNBT: NBTCompound

    init {
        val tags = HashMap<String, NBT>()
        tags["item"] = NBT.String(itemName)
        defaultNBT = NBTCompound(tags)
    }

    abstract fun getItemStack(): ItemStack

    open fun useItem(player: Player) {

    }
    open fun movedItem(player: Player) {

    }


}