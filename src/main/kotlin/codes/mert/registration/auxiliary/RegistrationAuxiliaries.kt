package codes.mert.registration.auxiliary

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import codes.mert.outbox.model.OutboxEvent
import codes.mert.registration.model.Person

class RegistrationAuxiliaries {
    companion object {
        fun createPersonCreatedEvent(entity : Person) : OutboxEvent {
            val mapper = ObjectMapper()
            val jsonNode = mapper.convertValue(
                entity,
                JsonNode::class.java
            )

            return OutboxEvent(
                entity.id,
                "PERSON_CREATED",
                jsonNode
            )
        }

        fun createPersonUpdatedEvent(entity : Person) : OutboxEvent {
            val mapper = ObjectMapper()
            val jsonNode = mapper.createObjectNode()
                .put("id", entity.id)
                .put("name", entity.name)
                .put("age", entity.age)

            return OutboxEvent(
                entity.id,
                "PERSON_UPDATED",
                jsonNode
            )
        }
    }
}
