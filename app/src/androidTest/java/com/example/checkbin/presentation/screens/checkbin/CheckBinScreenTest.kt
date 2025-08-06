package com.example.checkbin.presentation.screens.checkbin

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.checkbin.R
import com.example.checkbin.di.AppModule
import com.example.checkbin.domain.interfaces.repository.BinDataHistoryRepository
import com.example.checkbin.domain.interfaces.repository.BinDataRepository
import com.example.checkbin.domain.model.result.ApiResult
import com.example.checkbin.fake.BinDataFake.binDataDomainFake
import com.example.checkbin.presentation.MainActivity
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@UninstallModules(AppModule::class)
class CheckBinScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @BindValue
    @JvmField
    var binDataRepository: BinDataRepository = mockk()

    @BindValue
    @JvmField
    var binDataHistoryRepository: BinDataHistoryRepository = mockk()

    @Before
    fun setup() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            CheckBinScreen(
                onHistoryClick = {  }
            )
        }
    }

    @Test
    fun checkInitialState_uiCorrect() {
        val binInputTag = "binInput"
        val buttonLabel = composeTestRule.activity.resources.getString(R.string.button_check_bin)
        val fab = "historyFAB"

        composeTestRule
            .onNodeWithTag(binInputTag)
            .assertExists()

        composeTestRule
            .onNodeWithText(buttonLabel)
            .assertIsNotEnabled()

        composeTestRule
            .onNodeWithTag(fab)
            .assertExists()
    }

    @Test
    fun isButtonNotEnabled_withInputLessThan6Digits() {
        val binInputTag = "binInput"
        val buttonLabel = composeTestRule.activity.resources.getString(R.string.button_check_bin)

        composeTestRule
            .onNodeWithTag(binInputTag)
            .performTextInput("12345")

        composeTestRule
            .onNodeWithText(buttonLabel)
            .assertIsNotEnabled()
    }

    @Test
    fun isButtonEnabled_withCorrectInput() {
        val binInputTag = "binInput"
        val buttonLabel = composeTestRule.activity.resources.getString(R.string.button_check_bin)

        composeTestRule
            .onNodeWithTag(binInputTag)
            .performTextInput("123456")

        composeTestRule
            .onNodeWithText(buttonLabel)
            .assertIsEnabled()
    }

    @Test
    fun getCardBinData_withCorrectInput() = runTest {
        val binInput = "220070"
        val dataResponse = binDataDomainFake

        val buttonLabel = composeTestRule.activity.resources.getString(R.string.button_check_bin)
        val binDataCard = "binDataCard"
        val binInputTag = "binInput"
        coEvery { binDataRepository.getBinInfo(binInput) } returns ApiResult.Success(dataResponse)
        coEvery { binDataHistoryRepository.insertBinDataInHistory(any()) } returns Unit

        composeTestRule
            .onNodeWithTag(binInputTag)
            .performTextInput(binInput)

        composeTestRule
            .onNodeWithText(buttonLabel)
            .performClick()

        advanceUntilIdle()

        coVerify { binDataRepository.getBinInfo(binInput) }
        composeTestRule
            .onNodeWithTag(binDataCard)
            .assertIsDisplayed()
    }
}