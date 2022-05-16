package com.example.tutorials.spingboot.wicke.controller

import com.example.tutorials.spingboot.wicke.model.Bank
import com.example.tutorials.spingboot.wicke.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/banks")
class BankController(private val service: BankService) {

    @GetMapping
    fun getBanks(): Collection<Bank> = service.getBanks()
}