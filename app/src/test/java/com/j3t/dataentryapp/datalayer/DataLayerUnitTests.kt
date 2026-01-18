package com.j3t.dataentryapp.datalayer

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

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

        val field1 = DataField("testFieldName1", "testFieldValue1")
        val field2 = DataField("testFieldName2", "testFieldValue2")
        dataEntry1 = DataEntry(
            name = "testName1",
            notes = "testNotes",
            password = "testPassword"
        )
        dataEntry1.detailFields.add(field1)
        dataEntry1.detailFields.add(field2)

        val field3 = DataField("testFieldName3", "testFieldValue3")
        val field4 = DataField("testFieldName4", "testFieldValue4")
        dataEntry2 = DataEntry(
            name = "testName2",
            notes = "testNotes",
            password = "testPassword"
        )
        // Updated as per DataLayerUnitTests.html: field2 and field3 are added to dataEntry2
        dataEntry2.detailFields.add(field2)
        dataEntry2.detailFields.add(field3)
    }

    @Test
    fun createStoreTest() {
        // Updated as per DataLayerUnitTests.html
        dataLayer.createStore(listOf(dataEntry1, dataEntry2))
        assertEquals(1, getFileCount())
    }

    @Test
    fun saveStoreTest() {
        // Updated as per DataLayerUnitTests.html
        dataLayer.saveStore(listOf(dataEntry1))
        assertEquals(1, getFileCount())

        dataLayer.saveStore(listOf(dataEntry1, dataEntry2))
        assertEquals(2, getFileCount())
    }

    @Test
    fun loadStoreTest() {
        dataLayer.saveStore(listOf(dataEntry2, dataEntry1))
        
        val loadedList = dataLayer.loadStore()
        assertEquals(2, loadedList.size)
        
        assertEquals("testName1", loadedList[0].name)
        assertEquals("testNotes", loadedList[0].notes)
        assertEquals("testPassword", loadedList[0].password)
        
        assertEquals("testName2", loadedList[1].name)
    }

    @Test
    fun deleteStoreTest() {
        dataLayer.saveStore(listOf(dataEntry2))
        dataLayer.saveStore(listOf(dataEntry2, dataEntry1))
        
        dataLayer.deleteStore()
        assertEquals(0, getFileCount())
    }

    @Test
    fun storeExistsTest() {
        assertFalse(dataLayer.storeExists())
        dataLayer.saveStore(listOf(dataEntry2))
        assertTrue(dataLayer.storeExists())
    }

    private fun getFileCount(): Int {
        val files = context.filesDir.listFiles { _, name -> name.startsWith("info") && name.endsWith(".xml") }
        return files?.size ?: 0
    }
}
