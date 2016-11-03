package ru.web.project.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.web.project.model.entities.User;

import java.util.Optional;

/**
 * @author nivanov
 *         on 03.11.16.
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
