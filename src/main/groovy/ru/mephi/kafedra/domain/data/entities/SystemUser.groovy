package ru.mephi.kafedra.domain.data.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @author nivanov
 * on 11.04.17.
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
class SystemUser {

    @Id
    @NotNull
    @Size(min = 8, max = 100)
    String username

    @NotNull
    @Size(min = 8, max = 100)
    String password

    @Size(max = 100)
    String department

    @Size(max = 100)
    String email

    @NotNull
    @Size(min = 1, max = 200)
    String name

    @NotNull
    Boolean enabled

    @NotNull
    Boolean admin
}
