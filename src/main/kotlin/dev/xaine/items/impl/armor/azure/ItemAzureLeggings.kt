package dev.xaine.items.impl.armor.azure

import dev.xaine.items.Item
import dev.xaine.items.ItemType
import dev.xaine.items.attributes.Attribute
import dev.xaine.items.attributes.impl.IntAttribute
import dev.xaine.items.impl.Rarity
import dev.xaine.util.ComponentUtil
import dev.xaine.util.ItemUtil
import net.kyori.adventure.text.format.TextColor
import net.minestom.server.color.Color
import net.minestom.server.item.ItemHideFlag
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.item.metadata.LeatherArmorMeta
import net.minestom.server.tag.Tag
import java.util.*

class ItemAzureLeggings : Item("AZURE_LEGGINGS") {
    override val itemName = ComponentUtil.text("Azure Leggings").color(TextColor.color(0xbb80ff))
    override val itemStackBuilder = ItemStack.builder(Material.LEATHER_LEGGINGS).meta(LeatherArmorMeta::class.java) { meta ->
        meta.color(
            Color(0xbb80ff)
        )
        meta.hideFlag(ItemUtil.HIDE_ALL_FLAGS)
    }
    override val defaultRarity = Rarity.COMMON
    override val itemType = ItemType.HELMET

    override var defaultLore = this.formatLore("Some say the azure fell from the stars into the hands of the gods.")

    init {
        registerDefaultAttribute(Attribute.HEALTH, IntAttribute(10000))
        registerDefaultAttribute(Attribute.DEFENSE, IntAttribute(10000))
        registerDefaultAttribute(Attribute.CRIT_CHANCE, IntAttribute(10000))
        registerDefaultAttribute(Attribute.CRIT_DAMAGE, IntAttribute(10000))
        registerAttribute("rarity_upgrades", IntAttribute(1))
    }
}

