package com.example.slack_app_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SlackAppKotlinApplication

fun main(args: Array<String>) {
	runApplication<SlackAppKotlinApplication>(*args)
}
