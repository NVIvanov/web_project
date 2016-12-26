package ru.mephi.kafedra.data.entities

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @author nivanov
 * on 26.12.16.
 */
@Entity
class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    String relativePath

    @NotNull
    @Size(min = 1)
    String filename

    @ManyToOne
    SystemUser user
}
