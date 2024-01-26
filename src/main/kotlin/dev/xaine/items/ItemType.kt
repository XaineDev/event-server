package dev.xaine.items

enum class ItemType(private val suffix: String? = null) {
    ITEM(""),

    // armour
    HELMET,
    CHESTPLATE,
    LEGGINGS,
    BOOTS,

    // tools
    SWORD,
    PICKAXE,
    AXE,
    SHOVEL,
    ;

    override fun toString(): String {
        return suffix ?: this.name
    }
}