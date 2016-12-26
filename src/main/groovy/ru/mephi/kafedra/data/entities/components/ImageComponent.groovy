package ru.mephi.kafedra.data.entities.components

import javax.persistence.Entity
import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 26.12.16.
 */
@Entity
class ImageComponent extends Component {
    @NotNull
    String src
    Type type = Type.IMAGE
}
