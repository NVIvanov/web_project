package ru.mephi.kafedra.domain.data.entities

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @author nivanov
 * on 23.04.17.
 */

@Entity
class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    @Size(min = 1, max = 100)
    String title

    @ManyToOne
    Component parentComponent

    @NotNull
    Type type

    enum Type {
        ANYTYPE
    }
}