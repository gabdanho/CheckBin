package com.example.presentation.mapper

import com.example.checkbin.presentation.mapper.BinDataMapper.toPresentation
import com.example.fake.BinDataFake.binDataDomainFake
import org.junit.Assert.assertEquals
import org.junit.Test

class BinDataMapperTest {

    @Test
    fun `map BinDataDomain to BinDataPresentation`() {
        val initialBinData = binDataDomainFake
        val result = initialBinData.toPresentation()

        assertEquals(initialBinData.number.length.toString(), result.number.length.toString())
        assertEquals(initialBinData.number.luhn, result.number.luhn)

        assertEquals(initialBinData.scheme, result.scheme)
        assertEquals(initialBinData.type, result.type)
        assertEquals(initialBinData.brand, result.brand)
        assertEquals(initialBinData.prepaid, result.prepaid)

        assertEquals(initialBinData.country.numeric, result.country.numeric)
        assertEquals(initialBinData.country.alpha2, result.country.alpha2)
        assertEquals(initialBinData.country.name, result.country.name)
        assertEquals(initialBinData.country.emoji, result.country.emoji)
        assertEquals(initialBinData.country.currency, result.country.currency)
        assertEquals(initialBinData.country.latitude.toString(), result.country.latitude.toString())
        assertEquals(initialBinData.country.longitude.toString(), result.country.longitude.toString())

        assertEquals(initialBinData.bank.name, result.bank.name)
        assertEquals(initialBinData.bank.url, result.bank.url)
        assertEquals(initialBinData.bank.phone, result.bank.phone)
        assertEquals(initialBinData.bank.city, result.bank.city)
    }
}