package ru.mephi.kafedra.domain.data.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @author nivanov
 * on 11.04.17.
 */

@Entity
class SystemUser {

    @Id
    @NotNull
    @Size(min = 4, max = 100)
    String username

    @NotNull
    @Size(min = 8, max = 100)
    String password
    String department

    @NotNull
    @Size(min = 1, max = 200)
    String name

}
