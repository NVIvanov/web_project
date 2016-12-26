package ru.mephi.kafedra.data.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.mephi.kafedra.data.entities.SystemUser

/**
 * @author nivanov
 * on 26.12.16.
 */

@Repository
interface UserRepository extends CrudRepository<SystemUser, Long> {
    Optional<SystemUser> findByUsername(String username)
}