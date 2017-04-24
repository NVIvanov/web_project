package ru.mephi.kafedra.domain.data.entities

import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * @author nivanov
 *         on 23.04.17.
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = ['site_id', 'role']))
class SiteManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    @ManyToOne
    SystemUser user

    @NotNull
    @ManyToOne
    Site site

    @NotNull
    Role role

    enum Role {
        ADMIN, MODERATOR
    }
}
