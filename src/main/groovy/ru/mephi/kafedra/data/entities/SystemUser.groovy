package ru.mephi.kafedra.data.entities

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @author nivanov
 * on 26.12.16.
 */
@Entity
class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    @Size(min = 1, max = 100)
    String username

    @NotNull
    @Size(min = 1, max = 100)
    String name

    @NotNull
    @Size(min = 8, max = 100)
    String password

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    Set<Attachment> attachments

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    Set<Site> sites
}
