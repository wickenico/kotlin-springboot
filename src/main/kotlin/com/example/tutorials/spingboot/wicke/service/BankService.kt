package com.example.tutorials.spingboot.wicke.service

import com.example.tutorials.spingboot.wicke.datasource.BankDataSource
import com.example.tutorials.spingboot.wicke.model.Bank
import org.springframework.stereotype.Service
import javax.sql.CommonDataSource

@Service
class BankService(private val dataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)
    fun addBank(bank: Bank): Bank = dataSource.createBank(bank)
    fun updateBank(bank: Bank): Bank = dataSource.updateBank(bank)
    fun deleteBank(accountNumber: String): Unit = dataSource.deleteBank(accountNumber)

}