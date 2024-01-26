package dev.xaine.items.attributes

interface IAttributeValue<T> {

    var attributeValue: T
    fun get(): T? {
        return attributeValue
    }
}