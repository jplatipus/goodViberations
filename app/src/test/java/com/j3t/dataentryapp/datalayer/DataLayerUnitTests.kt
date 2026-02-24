package com.j3t.dataentryapp.datalayer

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class DataLayerUnitTests {

    private lateinit var context: Context
    private lateinit var dataLayer: DataLayer
    private lateinit var dataEntry1: DataEntry
    private lateinit var dataEntry2: DataEntry

    @Before
    fun beforeEach() {
        context = ApplicationProvider.getApplicationContext()
        dataLayer = DataLayer(context)
        dataLayer.deleteStore()

        // 1. field1
        val field1 = DataField("testFieldName1", "testFieldValue1")
        // 2. field2
        val field2 = DataField("testFieldName2", "testFieldValue2")
        // 3. dataEntry1
        dataEntry1 = DataEntry(
            name = "testName1",
            notes = "testNotes",
            password = "testPassword"
        )
        // 4. add fields to dataEntry1
        dataEntry1.detailFields.add(field1)
        dataEntry1.detailFields.add(field2)

        // 5. field3
        val field3 = DataField("testFieldName3", "testFieldValue3")
        // 6. field4
        val field4 = DataField("testFieldName4", "testFieldValue4")
        // 7. dataEntry2
        dataEntry2 = DataEntry(
            name = "testName2",
            notes = "testNotes",
            password = "testPassword"
        )
        // 8. add field2 and field3 to dataEntry2 as specified
        dataEntry2.detailFields.add(field2)
        dataEntry2.detailFields.add(field3)
    }

    @Test
    fun saveStoreTest() {
        val map1 = mutableMapOf<String, DataEntry>()
        map1[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        dataLayer.saveStore(map1)
        assertEquals(1, getFileCount())

        val map2 = mutableMapOf<String, DataEntry>()
        map2[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        map2[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.saveStore(map2)
        assertEquals(2, getFileCount())
    }

    @Test
    fun createStoreTest() {
        val map = mutableMapOf<String, DataEntry>()
        map[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        map[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.createStore(map)
        assertEquals(1, getFileCount())
    }

    @Test
    fun loadStoreTest() {
        val map = mutableMapOf<String, DataEntry>()
        map[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        map[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.saveStore(map)
        
        val loadedMap = dataLayer.loadStore()
        val values = loadedMap.values.toList()
        
        assertEquals("testName1", values[0].name)
        assertEquals("testname1", loadedMap.keys.first())
        assertEquals("testName2", values[1].name)
    }

    @Test
    fun loadEntryTest() {
        val map = mutableMapOf<String, DataEntry>()
        map[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        map[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.saveStore(map)

        val entry = dataLayer.loadEntry("testName2")
        assertEquals("testName2", entry.name)
        assertEquals("testPassword", entry.password)

        val notFoundEntry = dataLayer.loadEntry("testName")
        assertEquals("entry testName not found", notFoundEntry.name)
    }

    @Test
    fun deleteStoreTest() {
        val map1 = mutableMapOf<String, DataEntry>()
        map1[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.saveStore(map1)
        
        val map2 = mutableMapOf<String, DataEntry>()
        map2[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        map2[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.saveStore(map2)
        
        dataLayer.deleteStore()
        assertEquals(0, getFileCount())
    }

    @Test
    fun listEntryNamesTest() {
        val map = mutableMapOf<String, DataEntry>()
        map[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        map[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.saveStore(map)

        val names = dataLayer.listEntryNames()
        assertEquals(2, names.size)
        assertEquals("testName1", names[0])
        assertEquals("testName2", names[1])
    }

    @Test
    fun storeExistsTest() {
        assertFalse(dataLayer.storeExists())
        val map = mutableMapOf<String, DataEntry>()
        map[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.saveStore(map)
        assertTrue(dataLayer.storeExists())
    }

    private fun getFileCount(): Int {
        val files = context.filesDir.listFiles { _, name -> name.startsWith("info") && name.endsWith(".xml") }
        return files?.size ?: 0
    }
}
