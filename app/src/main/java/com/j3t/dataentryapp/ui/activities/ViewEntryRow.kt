package com.j3t.dataentryapp.ui.activities

data class ViewEntryRow(
    val label: String,
    val value: String,
    var isCopied: Boolean = false
)
