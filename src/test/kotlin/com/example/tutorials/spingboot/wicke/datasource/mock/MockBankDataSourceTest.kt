package com.example.tutorials.spingboot.wicke.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {

    private val mockDataSoure: MockBankDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of banks`() {

        // when
        val banks = mockDataSoure.retrieveBanks()

        // then
        assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `should provide some mock data `() {
        val banks = mockDataSoure.retrieveBanks()
        assertThat(banks).allMatch{it.accountNumber.isNotBlank()}
        assertThat(banks).allMatch{it.trust != 0.0}
        assertThat(banks).allMatch{it.transactionFee != 0}
    }
}