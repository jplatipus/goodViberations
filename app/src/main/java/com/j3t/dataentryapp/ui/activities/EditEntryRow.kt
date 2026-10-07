package com.j3t.dataentryapp.ui.activities

data class EditEntryRow(
    val label: String,
    val value: String,
    val detailFieldIndex: Int = -1 // Index in entryData.detailFields if it is a detail field
)
