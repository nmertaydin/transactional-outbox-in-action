package codes.mert.registration.model

import javax.persistence.*

@Entity
@Table(name = "person")
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    val name : String,
    val age: Int
    )
