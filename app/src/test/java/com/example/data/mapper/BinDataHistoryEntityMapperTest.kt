package com.example.data.mapper

import com.example.fake.BinDataFake.binDataDomainFake
import com.example.checkbin.data.mapper.BinDataHistoryEntityMapper.toBinDataHistoryEntity
import com.example.checkbin.data.mapper.BinDataHistoryEntityMapper.toDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class BinDataHistoryEntityMapperTest {

    @Test
    fun `convert BinDataDomain to BinDataHistoryEntity and back`() {
        val initialBinData = binDataDomainFake

        val entity = initialBinData.toBinDataHistoryEntity()
        val convertedBack = entity.toDomain()

        assertEquals(initialBinData.number.length, convertedBack.number.length)
        assertEquals(initialBinData.number.luhn, convertedBack.number.luhn)

        assertEquals(initialBinData.scheme, convertedBack.scheme)
        assertEquals(initialBinData.type, convertedBack.type)
        assertEquals(initialBinData.brand, convertedBack.brand)
        assertEquals(initialBinData.prepaid, convertedBack.prepaid)

        assertEquals(initialBinData.country.numeric, convertedBack.country.numeric)
        assertEquals(initialBinData.country.alpha2, convertedBack.country.alpha2)
        assertEquals(initialBinData.country.name, convertedBack.country.name)
        assertEquals(initialBinData.country.emoji, convertedBack.country.emoji)
        assertEquals(initialBinData.country.currency, convertedBack.country.currency)
        assertEquals(initialBinData.country.latitude, convertedBack.country.latitude)
        assertEquals(initialBinData.country.longitude, convertedBack.country.longitude)

        assertEquals(initialBinData.bank.name, convertedBack.bank.name)
        assertEquals(initialBinData.bank.url, convertedBack.bank.url)
        assertEquals(initialBinData.bank.phone, convertedBack.bank.phone)
        assertEquals(initialBinData.bank.city, convertedBack.bank.city)
    }
}