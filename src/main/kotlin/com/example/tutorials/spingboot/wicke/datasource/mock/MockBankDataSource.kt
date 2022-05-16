package com.example.tutorials.spingboot.wicke.datasource.mock

import com.example.tutorials.spingboot.wicke.datasource.BankDataSource
import com.example.tutorials.spingboot.wicke.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource: BankDataSource {

    val banks = listOf(Bank("1234", 3.0, 1),
    Bank("1334", 4.0, 2),
    Bank("1444", 5.0, 3))

    override fun retrieveBanks(): Collection<Bank> = banks
}