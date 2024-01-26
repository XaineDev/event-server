package item

import dev.xaine.items.ItemRepository
import dev.xaine.items.impl.Rarity
import dev.xaine.items.impl.armor.azure.ItemAzureHelmet
import kotlin.test.Test

class ItemRarityTest {
    @Test
    fun testRecombUpgrades() {
        for (rarity in Rarity.VALUES) {
            var newRarity = rarity
            if (rarity.canUpgrade()) {
                newRarity = Rarity[rarity.ordinal+1]
            }
            println("$rarity upgrade = ${rarity.canUpgrade()} -> $newRarity")
        }
    }

    @Test
    fun testItem() {
        val itemRepository = ItemRepository()
        itemRepository.register(ItemAzureHelmet::class)

        val item = itemRepository.get<ItemAzureHelmet>()
        assert(item != null)
        item!!
        println(item.getCurrentRarity().getComponent(item.isUpgraded(), item.itemType).toString())
    }

    @Test
    fun testingStuff() {
        val item = ItemAzureHelmet()
        println(item.getCurrentRarity().getComponent(item.isUpgraded(), item.itemType).toString())
    }
}