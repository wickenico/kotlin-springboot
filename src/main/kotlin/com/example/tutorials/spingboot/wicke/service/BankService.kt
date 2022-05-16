package com.example.tutorials.spingboot.wicke.service

import com.example.tutorials.spingboot.wicke.datasource.BankDataSource
import com.example.tutorials.spingboot.wicke.model.Bank
import org.springframework.stereotype.Service
import javax.sql.CommonDataSource

@Service
class BankService(private val dataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
}