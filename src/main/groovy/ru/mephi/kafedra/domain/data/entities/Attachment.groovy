package ru.mephi.kafedra.domain.data.entities

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @author nivanov
 * on 23.04.17.
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = ["placement_id", "filename"]))
class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    @Size(min = 1, max = 100)
    String title

    @Size(max = 2000)
    String description

    @NotNull
    String filename

    @NotNull
    @ManyToOne
    SystemUser author

    @NotNull
    @ManyToOne
    Site placement
}
