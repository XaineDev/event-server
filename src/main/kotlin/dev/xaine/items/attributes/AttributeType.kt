package dev.xaine.items.attributes

enum class AttributeType {
    COMBAT,
    GATHERING,
    WISDOM,
    MISC,
    EFFECT;

    companion object {
        val VALUES = entries.toList()
    }
}