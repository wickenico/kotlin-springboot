package com.example.tutorials.spingboot.wicke.datasource

import com.example.tutorials.spingboot.wicke.model.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank
    fun createBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
}