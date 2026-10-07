package com.j3t.dataentryapp.ui.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.j3t.dataentryapp.R

class EditEntryRowAdapter(
    context: Context,
    private val rows: List<EditEntryRow>,
    private val onArrowClickListener: (position: Int, row: EditEntryRow) -> Unit
) : ArrayAdapter<EditEntryRow>(context, R.layout.activity_edit_entry_row, rows) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.activity_edit_entry_row, parent, false)

        val row = rows[position]
        val lblFieldLabel = view.findViewById<TextView>(R.id.lblFieldLabel)
        val lblFieldValue = view.findViewById<TextView>(R.id.lblFieldValue)
        val btnRowArrow = view.findViewById<ImageButton>(R.id.btnRowArrow)

        lblFieldLabel.text = row.label
        lblFieldValue.text = row.value

        btnRowArrow.setOnClickListener {
            onArrowClickListener(position, row)
        }

        return view
    }
}
