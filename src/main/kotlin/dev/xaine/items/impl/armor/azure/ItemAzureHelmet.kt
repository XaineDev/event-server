package dev.xaine.items.impl.armor.azure

import dev.xaine.items.Item
import dev.xaine.items.ItemType
import dev.xaine.items.attributes.Attribute
import dev.xaine.items.attributes.IAttributeValue
import dev.xaine.items.attributes.impl.IntAttribute
import dev.xaine.items.impl.Rarity
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import java.util.*
import kotlin.collections.HashMap

class ItemAzureHelmet : Item {
    override val itemID: String
        get() = "AZURE_HELMET"
    override val itemName: Component
        get() = Component.text("Azure Helmet", TextColor.color(0xbb80ff)).decoration(TextDecoration.ITALIC, false)
    override val itemStackBuilder: ItemStack.Builder
        get() = ItemStack.builder(Material.LEATHER_HELMET)
    override val defaultRarity: Rarity
        get() = Rarity.DIVINE
    override val itemType: ItemType
        get() = ItemType.HELMET

    override var defaultLore: Array<Component> = this.formatLore("Some say the azure fell from the stars into the hands of the gods.")
    override val defaultAttributes: EnumMap<Attribute, Float> = EnumMap(Attribute::class.java)
    override val specialAttributes: HashMap<String, IAttributeValue<*>> = HashMap()

    init {
        //defaultAttributes[Attribute.HEALTH] = 10000f
        specialAttributes["rarity_upgrades"] = IntAttribute(1)
    }

}