package com.example.meliapp


import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.meliapp.viewModels.ItemViewModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test


class ItemSearchViewModelTest : TestCase() {

    private lateinit var viewModel: ItemViewModel

    lateinit var instrumentationContext: Context

    @Before
    fun setup(){
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        //viewModel = ItemViewModel()
    }

    @Test
    fun testGetItemList(){
        //todo
    }

    fun testGetErrorMessage() {
        //todo
    }

    fun testGetItemsByQuery() {
        //todo
    }

}