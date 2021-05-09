package codes.mert.registration.service

import codes.mert.outbox.OutboxEventPublisher
import codes.mert.registration.auxiliary.RegistrationAuxiliaries
import codes.mert.registration.model.Person
import codes.mert.registration.repository.PersonRepository
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@ComponentScan("codes.mert")
class PersonService(
    private val personRepository: PersonRepository,
    private val eventPublisher: OutboxEventPublisher
    )
{
    fun getAll(): List<Person> =
        personRepository.findAll()

    @Transactional
    fun add(entity: Person): ResponseEntity<Person> {
        personRepository.save(entity)
        eventPublisher.fire(RegistrationAuxiliaries.createPersonCreatedEvent(entity))
        return ResponseEntity.ok(entity)
    }

    fun get(entityId: Long): ResponseEntity<Person> =
        personRepository.findById(entityId).map { task ->
            ResponseEntity.ok(task)
        }.orElse(ResponseEntity.notFound().build())

    @Transactional
    fun put(entityId: Long, entity: Person): ResponseEntity<Person> =
        personRepository.findById(entityId).map { currentEntity ->
            val updatedEntity: Person =
                currentEntity
                    .copy(
                        name = entity.name,
                        age = entity.age
                    )
            personRepository.save(updatedEntity)
            eventPublisher.fire(RegistrationAuxiliaries.createPersonUpdatedEvent(updatedEntity))
            ResponseEntity.ok().body(updatedEntity)
        }.orElse(ResponseEntity.notFound().build())

    fun delete(entityId: Long): ResponseEntity<Void> =
        personRepository.findById(entityId).map { task ->
            personRepository.delete(task)
            ResponseEntity<Void>(HttpStatus.ACCEPTED)
        }.orElse(ResponseEntity.notFound().build())

}