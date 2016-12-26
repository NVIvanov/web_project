package ru.mephi.kafedra.data.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import ru.mephi.kafedra.data.entities.components.Component

/**
 * @author nivanov
 * on 26.12.16.
 */
@NoRepositoryBean
interface ComponentRepository<T extends Component> extends CrudRepository<T, Long> {
}