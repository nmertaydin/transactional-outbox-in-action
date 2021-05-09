package codes.mert.outbox.service

import codes.mert.outbox.model.Outbox
import codes.mert.outbox.model.OutboxEvent
import codes.mert.outbox.repository.OutboxRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.util.*

@Service
class OutboxService(private val outboxRepository: OutboxRepository) {

    @EventListener()
    fun handleOutboxEvent(event: OutboxEvent) {
        val uuid = UUID.randomUUID()
        val entity = Outbox(
            uuid.toString(),
            event.aggregateId!!,
            event.eventType,
            event.payload.toString(),
            Date()
        )
        outboxRepository.save(entity)
        outboxRepository.delete(entity)
    }

}