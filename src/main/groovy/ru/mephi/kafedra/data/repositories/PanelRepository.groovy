package ru.mephi.kafedra.data.repositories

import org.springframework.stereotype.Repository
import ru.mephi.kafedra.data.entities.components.PanelComponent

/**
 * @author nivanov
 * on 26.12.16.
 */
@Repository
interface PanelRepository extends ComponentRepository<PanelComponent> {
}