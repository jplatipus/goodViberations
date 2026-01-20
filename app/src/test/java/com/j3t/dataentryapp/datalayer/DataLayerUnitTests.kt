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
        // 2. field2 (named field12 in doc, but using field2 for clarity/consistency with earlier)
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
        // 1. saveStore with dataEntry1
        val map1 = mutableMapOf<String, DataEntry>()
        map1[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        dataLayer.saveStore(map1)
        
        // 2. count files == 1
        assertEquals(1, getFileCount())

        // 3. saveStore with dataEntry1 and dataEntry2
        val map2 = mutableMapOf<String, DataEntry>()
        map2[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        map2[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.saveStore(map2)
        
        // 4. count files == 2
        assertEquals(2, getFileCount())
    }

    @Test
    fun createStoreTest() {
        // createStore with map of dataEntry1 and dataEntry2
        val map = mutableMapOf<String, DataEntry>()
        map[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        map[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.createStore(map)
        
        // count files == 1
        assertEquals(1, getFileCount())
    }

    @Test
    fun loadStoreTest() {
        // 1. saveStore with map of dataEntry2 and dataEntry1
        val map = mutableMapOf<String, DataEntry>()
        map[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        map[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.saveStore(map)
        
        // 2. loadStore and check
        val loadedMap = dataLayer.loadStore()
        val values = loadedMap.values.toList()
        
        // First entry: testName1
        assertEquals("testName1", values[0].name)
        assertEquals("testNotes", values[0].notes)
        assertEquals("testPassword", values[0].password)
        assertEquals("testname1", loadedMap.keys.first())
        
        // Second entry: testName2
        assertEquals("testName2", values[1].name)
        assertEquals("testname2", loadedMap.keys.toList()[1])
    }

    @Test
    fun deleteStoreTest() {
        // saveStore with dataEntry2
        val map1 = mutableMapOf<String, DataEntry>()
        map1[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.saveStore(map1)
        
        // saveStore with dataEntry2 and dataEntry1
        val map2 = mutableMapOf<String, DataEntry>()
        map2[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        map2[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.saveStore(map2)
        
        // deleteStore
        dataLayer.deleteStore()
        
        // check no files
        assertEquals(0, getFileCount())
    }

    @Test
    fun storeExistsTest() {
        // returns false
        assertFalse(dataLayer.storeExists())
        
        // saveStore with dataEntry2
        val map = mutableMapOf<String, DataEntry>()
        map[dataEntry2.name.lowercase(Locale.getDefault())] = dataEntry2
        dataLayer.saveStore(map)
        
        // returns true
        assertTrue(dataLayer.storeExists())
    }

    private fun getFileCount(): Int {
        val files = context.filesDir.listFiles { _, name -> name.startsWith("info") && name.endsWith(".xml") }
        return files?.size ?: 0
    }
}
