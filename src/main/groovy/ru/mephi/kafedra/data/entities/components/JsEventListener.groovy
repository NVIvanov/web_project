package ru.mephi.kafedra.data.entities.components

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @author nivanov
 * on 26.12.16.
 */
@Entity
class JsEventListener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    @Size(min = 1)
    String event

    @NotNull
    @Size(min = 1)
    String js

    @ManyToOne
    Component component
}
