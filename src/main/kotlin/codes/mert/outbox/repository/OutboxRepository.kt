package codes.mert.outbox.repository

import codes.mert.outbox.model.Outbox
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface OutboxRepository : JpaRepository<Outbox, Long>