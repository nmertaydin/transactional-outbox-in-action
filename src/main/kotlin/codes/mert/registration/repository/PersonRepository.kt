package codes.mert.registration.repository

import codes.mert.registration.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface PersonRepository : JpaRepository<Person, Long>