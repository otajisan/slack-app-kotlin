package com.example.slack_app_kotlin

import com.example.slack_app_kotlin.domain.SlackNotificationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/slack")
class SlackNotificationController(
    private val slackNotificationService: SlackNotificationService
) {

    /**
     * Send Slack message.
     *
     * @param message
     * @return
     */
    @Operation(summary = "Send Slack message.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Success"),
            ApiResponse(responseCode = "400", description = "Bad request"),
            ApiResponse(responseCode = "401", description = "Unauthorized"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = ["/send/{message}"])
    fun sendMessage(@PathVariable message: String): ResponseEntity<String> {
        slackNotificationService.notify(message)
        return ResponseEntity("ok", HttpStatus.OK)
    }

    /**
     * Send Reply Slack message.
     *
     * @param message
     * @return
     */
    @Operation(summary = "Send Reply Slack message.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Success"),
            ApiResponse(responseCode = "400", description = "Bad request"),
            ApiResponse(responseCode = "401", description = "Unauthorized"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = ["/reply/{ts}"])
    fun reply(@PathVariable ts: String): ResponseEntity<String> {
        slackNotificationService.reply(ts)
        return ResponseEntity("ok", HttpStatus.OK)
    }
}