package com.example.slack_app_kotlin.infrastructure

import com.example.slack_app_kotlin.domain.SlackChatResponse
import com.example.slack_app_kotlin.domain.SlackMessage
import com.example.slack_app_kotlin.logger
import com.slack.api.methods.MethodsClient
import com.slack.api.methods.SlackApiException
import com.slack.api.model.Attachment
import org.springframework.stereotype.Repository
import java.io.IOException
import java.lang.RuntimeException

@Repository
class SlackAppRepository(
    private val slackMethodsClient: MethodsClient,
) {

    companion object {
        private val log by logger()
    }

    fun sendChat(message: SlackMessage): SlackChatResponse {
        try {
            val attachments = mutableListOf<Attachment>()
            attachments.add(
                Attachment.builder()
                    .fallback(message.attachmentFallbackMessage)
                    .blocks(message.blocks)
                    .build()
            )

            val response = slackMethodsClient.chatPostMessage { req ->
                if (message.threadTs != null) {
                    // reply
                    req.threadTs(message.threadTs)
                        .replyBroadcast(false)
                }

                // normal
                req.channel(message.channel)
                    .text(message.headerText)
                    .attachments(attachments)
            }

            //if (response.code != 200) throw RuntimeException("Slack API error. message: ${response.body}")
            log.info("Slack notification response. response: $response")

            return SlackChatResponse(
                channel = response.channel,
                ts = response.ts
            )

        } catch (ex: SlackApiException) {
            throw RuntimeException("Slack API error", ex)
        } catch (ex: IOException) {
            throw RuntimeException("Slack API error", ex)
        }
    }
}