package com.example.checkbin.presentation.screens.history

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.checkbin.R
import com.example.checkbin.di.AppModule
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.domain.interfaces.repository.BinDataRepository
import com.example.checkbin.domain.model.result.DbResult
import com.example.checkbin.fake.BinDataFake.binDataDomainFake
import com.example.checkbin.presentation.MainActivity
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class HistoryBinDataScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @BindValue
    @JvmField
    var binDataHistoryRepository: BinDataHistoryRepository = mockk()

    @BindValue
    @JvmField
    var binDataDataRepository: BinDataRepository = mockk()

    @Before
    fun setup() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            HistoryBinDataScreen(
                onBackClick = {  }
            )
        }
    }

    @Test
    fun showTextHistoryNotExist_whenHistoryIsEmpty() = runTest {

        coEvery { binDataHistoryRepository.getBinDataHistory() } returns DbResult.Success(emptyList())

        composeTestRule
            .onNodeWithText(composeTestRule.activity.resources.getString(R.string.text_history_not_exists))
            .assertIsDisplayed()
    }

    @Test
    fun showBins_whenHistoryIsNotEmpty() = runTest {

        val binList = listOf(
            binDataDomainFake.copy(brand = "ozon"),
            binDataDomainFake.copy(brand = "tinkoff"),
            binDataDomainFake.copy(brand = "sber")
        )

        val binDataCard = "binDataCard"

        coEvery { binDataHistoryRepository.getBinDataHistory() } returns DbResult.Success(binList)

        val countCards = composeTestRule
            .onAllNodesWithTag(binDataCard)
            .fetchSemanticsNodes()
            .size

        assertEquals(countCards, 3)
        composeTestRule
            .onNodeWithText("ozon")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("sber")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("tinkoff")
            .assertIsDisplayed()
    }
}