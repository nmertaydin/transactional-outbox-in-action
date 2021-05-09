package codes.mert.outbox.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "outbox")
data class Outbox(
    @Id
    val uuid : String,
    val aggregateId : Long,
    val eventType : String,
    val payload : String,
    val createdOn : Date
)