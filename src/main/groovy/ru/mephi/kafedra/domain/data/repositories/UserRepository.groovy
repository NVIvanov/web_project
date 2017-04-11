package ru.mephi.kafedra.domain.data.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.mephi.kafedra.domain.data.entities.SystemUser

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 11.04.17.
 */

@Repository
interface UserRepository extends CrudRepository<SystemUser, String> {
    Optional<SystemUser> findByUsername(@NotNull String username)
}
