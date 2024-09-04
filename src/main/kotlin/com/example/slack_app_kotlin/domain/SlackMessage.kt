package com.example.slack_app_kotlin.domain

import com.slack.api.model.block.LayoutBlock

data class SlackMessage(
    val channel: String,
    val headerText: String,
    val attachmentFallbackMessage: String,
    val blocks: List<LayoutBlock>? = null,
    val threadTs: String? = null
)