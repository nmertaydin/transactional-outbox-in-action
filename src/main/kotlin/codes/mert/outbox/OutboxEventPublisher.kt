package codes.mert.outbox

import codes.mert.outbox.model.OutboxEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.stereotype.Component

@Component
class OutboxEventPublisher : ApplicationEventPublisherAware {
    private var publisher: ApplicationEventPublisher? = null
    override fun setApplicationEventPublisher(applicationEventPublisher: ApplicationEventPublisher) {
        publisher = applicationEventPublisher
    }

    fun fire(outboxEvent: OutboxEvent?) {
        publisher!!.publishEvent(outboxEvent!!)
    }
}