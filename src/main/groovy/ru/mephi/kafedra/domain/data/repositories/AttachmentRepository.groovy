package ru.mephi.kafedra.domain.data.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.mephi.kafedra.domain.data.entities.Attachment

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 23.04.17.
 */

@Repository
interface AttachmentRepository extends CrudRepository<Attachment, Long> {
    Set<Attachment> findByAuthorUsername(@NotNull String username)

    Set<Attachment> findByPlacementId(@NotNull Long siteId)
}