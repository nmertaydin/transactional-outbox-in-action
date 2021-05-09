package codes.mert.registration.resource

import codes.mert.registration.model.Person
import codes.mert.registration.service.PersonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/api/person")
class PersonResource(private val personService: PersonService) {

    @GetMapping
    fun getAll(): List<Person> =
        personService.getAll()

    @PostMapping
    fun add(@Valid @RequestBody entity: Person): ResponseEntity<Person> =
        personService.add(entity)

    @GetMapping("/{id}")
    fun get(@PathVariable(value="id") entityId: Long): ResponseEntity<Person> =
        personService.get(entityId)

    @PutMapping("/{id}")
    fun update(
        @PathVariable(value = "id") entityId: Long,
        @Valid @RequestBody newEntity: Person
    ): ResponseEntity<Person> =
        personService.put(entityId, newEntity)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") entityId: Long): ResponseEntity<Void> =
        personService.delete(entityId)
}