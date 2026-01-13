package com.j3t.dataentryapp.datalayer

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class DataLayer(private val context: Context) {

    private val filesDir: File = context.filesDir

    fun createStore(data: List<DataEntry>): List<DataEntry> {
        val files = filesDir.listFiles { _, name -> name.startsWith("info") && name.endsWith(".xml") }
        if (files != null && files.size >= 5) {
            val oldestFile = files.minByOrNull { it.lastModified() }
            oldestFile?.delete()
        }

        val timeStamp = SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault()).format(Date())
        val fileName = "info$timeStamp.xml"
        val file = File(filesDir, fileName)

        val xmlString = convertToXml(data)
        file.writeText(xmlString)

        return data
    }

    fun saveStore(data: List<DataEntry>): List<DataEntry> {
        return createStore(data)
    }

    fun loadStore(): List<DataEntry> {
        val files = filesDir.listFiles { _, name -> name.startsWith("info") && name.endsWith(".xml") }
        if (files.isNullOrEmpty()) {
            return mutableListOf()
        }

        val latestFile = files.maxByOrNull { it.lastModified() }
        return latestFile?.let { parseXml(it.readText()) } ?: mutableListOf()
    }

    fun deleteStore() {
        filesDir.listFiles()?.forEach { it.delete() }
    }

    fun storeExists(): Boolean {
        val files = filesDir.listFiles { _, name -> name.startsWith("info") && name.endsWith(".xml") }
        return !files.isNullOrEmpty()
    }

    private fun convertToXml(data: List<DataEntry>): String {
        val sb = StringBuilder()
        sb.append("<entries>\n")
        data.forEach { entry ->
            sb.append("  <entry name=\"${escapeXml(entry.name)}\" notes=\"${escapeXml(entry.notes)}\" password=\"${escapeXml(entry.password)}\" creation=\"${entry.creationDateTime.time}\" modified=\"${entry.lastModifiedDateTime.time}\" version=\"${entry.version}\">\n")
            entry.detailFields.forEach { field ->
                sb.append("    <field name=\"${escapeXml(field.name)}\" value=\"${escapeXml(field.value)}\" />\n")
            }
            sb.append("  </entry>\n")
        }
        sb.append("</entries>")
        return sb.toString()
    }

    private fun parseXml(xml: String): List<DataEntry> {
        val entries = mutableListOf<DataEntry>()
        val entryRegex = "<entry name=\"(.*?)\" notes=\"(.*?)\" password=\"(.*?)\" creation=\"(.*?)\" modified=\"(.*?)\" version=\"(.*?)\">(.*?)</entry>".toRegex(RegexOption.DOT_MATCHES_ALL)
        val fieldRegex = "<field name=\"(.*?)\" value=\"(.*?)\" />".toRegex()

        entryRegex.findAll(xml).forEach { matchResult ->
            val (name, notes, password, creation, modified, version, fieldsXml) = matchResult.destructured
            val entry = DataEntry(
                name = unescapeXml(name),
                notes = unescapeXml(notes),
                password = unescapeXml(password),
                creationDateTime = Date(creation.toLong()),
                lastModifiedDateTime = Date(modified.toLong()),
                version = version.toInt()
            )
            fieldRegex.findAll(fieldsXml).forEach { fieldMatch ->
                val (fName, fValue) = fieldMatch.destructured
                entry.detailFields.add(DataField(unescapeXml(fName), unescapeXml(fValue)))
            }
            entries.add(entry)
        }
        return entries
    }

    private fun escapeXml(s: String): String = s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;")
    private fun unescapeXml(s: String): String = s.replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">").replace("&quot;", "\"").replace("&apos;", "'")
}
