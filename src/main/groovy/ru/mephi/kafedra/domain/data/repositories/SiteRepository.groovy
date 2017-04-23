package ru.mephi.kafedra.domain.data.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.mephi.kafedra.domain.data.entities.Site

/**
 * @author nivanov
 * on 23.04.17.
 */

@Repository
interface SiteRepository extends CrudRepository<Site, Long> {

}