package com.example.slack_app_kotlin.domain

import com.example.slack_app_kotlin.infrastructure.SlackAppRepository
import com.example.slack_app_kotlin.config.SlackAppConfig
import com.slack.api.model.kotlin_extension.block.element.ButtonStyle
import com.slack.api.model.kotlin_extension.block.withBlocks
import org.springframework.stereotype.Service

@Service
class SlackNotificationService(
    private val slackAppConfig: SlackAppConfig,
    private val slackAppRepository: SlackAppRepository
) {

    fun notify(message: String) {

        val blocks = withBlocks {
            section {
                markdownText(":tada: _[Example]_ *This is test message!!*\n${message}")
            }
        }

        val chatResponse = slackAppRepository.sendChat(
            SlackMessage(
                channel = slackAppConfig.channel,
                headerText = "Succeeded!!",
                attachmentFallbackMessage = "Attachments fallback",
                blocks = blocks
            )
        )

        slackAppRepository.sendChat(
            SlackMessage(
                channel = chatResponse.channel,
                headerText = "Reply simulator",
                attachmentFallbackMessage = "Reply button",
                blocks = withBlocks {
                    actions {
                        elements {
                            // https://api.slack.com/reference/block-kit/block-elements#button
                            button {
                                text("Send reply")
                                url("http://localhost:9990/slack/reply/${chatResponse.ts}")
                                style(ButtonStyle.PRIMARY)
                            }
                        }
                    }
                }
            )
        )
    }

    fun reply(ts: String) {
        slackAppRepository.sendChat(
            SlackMessage(
                channel = slackAppConfig.channel,
                headerText = "reply to thread",
                attachmentFallbackMessage = "Reply message",
                threadTs = ts,
                blocks = withBlocks {
                    section {
                        markdownText("Reply to thread. ts: $ts")
                    }
                }
            )
        )
    }
}