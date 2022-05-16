package com.example.tutorials.spingboot.wicke.datasource

import com.example.tutorials.spingboot.wicke.model.Bank

interface BankDataSource {

    fun retrieveBanks(): Collection<Bank>
}