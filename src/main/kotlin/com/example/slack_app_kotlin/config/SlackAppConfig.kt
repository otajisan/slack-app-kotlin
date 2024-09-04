package com.example.slack_app_kotlin.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "slack.app")
class SlackAppConfig {
    lateinit var token: String
    lateinit var channel: String
}
