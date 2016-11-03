package ru.web.project.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.web.project.model.entities.PageNode;

import java.util.Optional;

/**
 * @author nivanov
 *         on 03.11.16.
 */
@Repository
public interface PageNodeRepository extends CrudRepository<PageNode, Long> {
    Optional<PageNode> findByRelativeToParentAndSiteRelativePath(String relativeToParent,
                                                                 String relativePath);
}
