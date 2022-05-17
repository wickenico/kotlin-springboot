package com.example.tutorials.spingboot.wicke.controller

import com.example.tutorials.spingboot.wicke.model.Bank
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    var objectMapper: ObjectMapper

) {

    val baseUrl = "/api/banks"

    @Nested
    @DisplayName("getBanks()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`() {

            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType((MediaType.APPLICATION_JSON)) }
                    jsonPath("$[0].accountNumber") { value("1234") }
                }
        }
    }

    @Nested
    @DisplayName("getBank()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return the bank with the given account number`() {

            val accountNumber = 1234

            mockMvc.get("$baseUrl/$accountNumber")

                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") {value(3.14)}
                    jsonPath("$.transactionFee") {value("17")}
                }
        }

        @Test
        fun `should return Not found if the account number does not exist`() {
            val accountNumber = "does_not_exist"

            mockMvc.get("$baseUrl/$accountNumber")

                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }

        }
    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewBank {
        @Test
        fun `should add the new bank`() {

            val newBank = Bank("ac123", 31.415, 2)

            val perfomPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }
            perfomPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") { value("ac123") }
                    jsonPath("$.trust") { value(31.415) }
                    jsonPath("$.transactionFee") {
                        value("2")
                    }
                }
        }

        @Test
        fun `should return BAD REQUEST if bank with given account number already exists`() {

            val invalidBank = Bank("1234", 31.415, 2)

            val perfomPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
            perfomPost
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }
}