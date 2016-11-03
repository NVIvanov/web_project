package ru.web.project.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.web.project.model.entities.Site;

import java.util.Optional;

/**
 * @author nivanov
 *         on 03.11.16.
 */

@Repository
public interface SiteRepository extends CrudRepository<Site, Long> {
    Optional<Site> findByRelativePath(String path);
}
