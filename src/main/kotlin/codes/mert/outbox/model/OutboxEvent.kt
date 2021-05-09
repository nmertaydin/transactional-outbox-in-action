package codes.mert.outbox.model

import com.fasterxml.jackson.databind.JsonNode

data class OutboxEvent(
    val aggregateId : Long?,
    val eventType : String,
    val payload : JsonNode
)