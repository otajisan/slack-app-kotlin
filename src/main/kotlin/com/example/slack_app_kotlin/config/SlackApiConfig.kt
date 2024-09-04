package com.example.slack_app_kotlin.config

import com.slack.api.Slack
import com.slack.api.methods.MethodsClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SlackApiConfig(
    private val slackAppConfig: SlackAppConfig
) {
    @Bean
    fun slackMethodsClient(): MethodsClient? =
        Slack.getInstance().methods(slackAppConfig.token)
}