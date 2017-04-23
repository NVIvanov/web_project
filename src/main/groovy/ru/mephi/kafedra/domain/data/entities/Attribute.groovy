package ru.mephi.kafedra.domain.data.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 23.04.17.
 */

@Entity
class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    String key

    @NotNull
    String value
}
