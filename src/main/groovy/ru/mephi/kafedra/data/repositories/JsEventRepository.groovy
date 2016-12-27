package ru.mephi.kafedra.data.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.mephi.kafedra.data.entities.components.JsEventListener

/**
 * @author nivanov
 * on 26.12.16.
 */
@Repository
interface JsEventRepository extends CrudRepository<JsEventListener, Long> {

}