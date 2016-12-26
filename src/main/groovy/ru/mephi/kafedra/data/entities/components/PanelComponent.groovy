package ru.mephi.kafedra.data.entities.components

import javax.persistence.Entity
import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 26.12.16.
 */

@Entity
class PanelComponent extends Component {

    @NotNull
    Integer borderRadius

    Type type = Type.PANEL
}
