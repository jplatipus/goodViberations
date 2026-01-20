package com.j3t.dataentryapp.datalayer

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class DataLayer(private val context: Context) {

    private val filesDir: File = context.filesDir

    fun createStore(data: Map<String, DataEntry>): Map<String, DataEntry> {
        val files = filesDir.listFiles { _, name -> name.startsWith("info") && name.endsWith(".xml") }
        if (files != null && files.size >= 5) {
            val oldestFile = files.minByOrNull { it.lastModified() }
            oldestFile?.delete()
        }

        val timeStamp = SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault()).format(Date())
        val randomInt = (0..100).random()
        val fileName = "info$timeStamp$randomInt.xml"
        val file = File(filesDir, fileName)

        // Sort data by name (case-insensitive) as specified. 
        // TreeMap handles sorting by key automatically.
        val sortedData = data.toSortedMap(String.CASE_INSENSITIVE_ORDER)

        val xmlString = convertToXml(sortedData)
        file.writeText(xmlString)

        return sortedData
    }

    fun saveStore(data: Map<String, DataEntry>): Map<String, DataEntry> {
        return createStore(data)
    }

    fun loadStore(): Map<String, DataEntry> {
        val files = filesDir.listFiles { _, name -> name.startsWith("info") && name.endsWith(".xml") }
        if (files.isNullOrEmpty()) {
            return mutableMapOf()
        }

        val latestFile = files.maxByOrNull { it.lastModified() }
        val loadedData = latestFile?.let { parseXml(it.readText()) } ?: mutableMapOf()
        
        // Return sorted map
        return loadedData.toSortedMap(String.CASE_INSENSITIVE_ORDER)
    }

    fun deleteStore() {
        filesDir.listFiles { _, name -> name.startsWith("info") && name.endsWith(".xml") }?.forEach { it.delete() }
    }

    fun storeExists(): Boolean {
        val files = filesDir.listFiles { _, name -> name.startsWith("info") && name.endsWith(".xml") }
        return !files.isNullOrEmpty()
    }

    private fun convertToXml(data: Map<String, DataEntry>): String {
        val sb = StringBuilder()
        sb.append("<entries>\n")
        data.values.forEach { entry ->
            sb.append("  <entry name=\"${escapeXml(entry.name)}\" notes=\"${escapeXml(entry.notes)}\" password=\"${escapeXml(entry.password)}\" creation=\"${entry.creationDateTime.time}\" modified=\"${entry.lastModifiedDateTime.time}\" version=\"${entry.version}\">\n")
            entry.detailFields.forEach { field ->
                sb.append("    <field name=\"${escapeXml(field.name)}\" value=\"${escapeXml(field.value)}\" />\n")
            }
            sb.append("  </entry>\n")
        }
        sb.append("</entries>")
        return sb.toString()
    }

    private fun parseXml(xml: String): Map<String, DataEntry> {
        val entries = mutableMapOf<String, DataEntry>()
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
                val field = DataField(unescapeXml(fName), unescapeXml(fValue))
                // Ensure field uniqueness during load as well
                if (!entry.detailFields.any { it.name.equals(field.name, ignoreCase = true) }) {
                    entry.detailFields.add(field)
                }
            }
            entries[entry.name.lowercase(Locale.getDefault())] = entry
        }
        return entries
    }

    private fun escapeXml(s: String): String = s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;")
    private fun unescapeXml(s: String): String = s.replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">").replace("&quot;", "\"").replace("&apos;", "'")
}
