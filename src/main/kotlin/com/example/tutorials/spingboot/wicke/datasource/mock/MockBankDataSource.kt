package com.example.tutorials.spingboot.wicke.datasource.mock

import com.example.tutorials.spingboot.wicke.datasource.BankDataSource
import com.example.tutorials.spingboot.wicke.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource: BankDataSource {

    val banks = mutableListOf(Bank("1234", 3.14, 17),
    Bank("1334", 4.0, 2),
    Bank("1444", 5.0, 3))

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank = banks.firstOrNull() { it.accountNumber == accountNumber }
        ?: throw NoSuchElementException("Could not find a bank with account Number $accountNumber")

    override fun createBank(bank: Bank): Bank {
        if (banks.any {it.accountNumber == bank.accountNumber }) {
            throw IllegalArgumentException("Bank with ${bank.accountNumber} already exists!")
        }
        banks.add(bank)

        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber} ?: throw NoSuchElementException("Could not find a bank.")

        banks.remove(currentBank)
        banks.add(bank)

        return bank
    }


}