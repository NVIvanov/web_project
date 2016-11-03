package ru.web.project.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.web.project.model.entities.SiteDeveloper;

import java.util.Collection;

/**
 * @author nivanov
 *         on 03.11.16.
 */
@Repository
public interface SiteDeveloperRepository extends CrudRepository<SiteDeveloper, Long> {
    Collection<SiteDeveloper> findByUserEmail(String email);
    Collection<SiteDeveloper> findBySiteRelativePath(String relativePath);
}
