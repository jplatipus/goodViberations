package com.j3t.dataentryapp.ui.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.j3t.dataentryapp.R

class ViewEntryAdapter(context: Context, private val rows: List<ViewEntryRow>) :
    ArrayAdapter<ViewEntryRow>(context, R.layout.activity_view_entry_row, rows) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.activity_view_entry_row, parent, false)

        val row = rows[position]
        val lblLabel = view.findViewById<TextView>(R.id.lblLabel)
        val lblValue = view.findViewById<TextView>(R.id.lblValue)
        val imgCopyStatus = view.findViewById<ImageView>(R.id.imgCopyStatus)

        lblLabel.text = row.label
        lblValue.text = row.value

        if (row.isCopied) {
            imgCopyStatus.setImageResource(R.drawable.copied)
        } else {
            imgCopyStatus.setImageResource(R.drawable.copyempty)
        }

        return view
    }
}
