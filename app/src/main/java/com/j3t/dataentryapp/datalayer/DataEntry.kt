package com.j3t.dataentryapp.datalayer

import java.util.Date

data class DataEntry(
    var name: String,
    var notes: String = "",
    var password: String = "",
    val creationDateTime: Date = Date(),
    var lastModifiedDateTime: Date = Date(),
    val version: Int = 1,
    val detailFields: MutableList<DataField> = mutableListOf()
) {
    init {
        require(name.isNotEmpty()) { "Name cannot be empty" }
    }

    fun isNameMatch(otherName: String): Boolean {
        return name.equals(otherName, ignoreCase = true)
    }

    fun addDetailField(field: DataField) {
        if (detailFields.any { it.name.equals(field.name, ignoreCase = true) }) {
            throw IllegalArgumentException("Field name '${field.name}' must be unique within an entry.")
        }
        detailFields.add(field)
    }
}
