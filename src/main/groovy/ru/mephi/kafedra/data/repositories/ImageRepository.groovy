package ru.mephi.kafedra.data.repositories

import org.springframework.stereotype.Repository
import ru.mephi.kafedra.data.entities.components.ImageComponent

/**
 * @author nivanov
 * on 26.12.16.
 */
@Repository
interface ImageRepository extends ComponentRepository<ImageComponent> {
}