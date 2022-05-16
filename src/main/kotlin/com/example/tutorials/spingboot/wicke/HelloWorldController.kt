package com.example.tutorials.spingboot.wicke

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/hello")
class HelloWorldController {

    @GetMapping
    fun helloWorld(): String {
        return "This is a rest endpoint! "
    }
}