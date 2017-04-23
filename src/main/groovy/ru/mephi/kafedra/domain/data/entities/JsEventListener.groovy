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
class JsEventListener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    String event

    @NotNull
    String code
}
