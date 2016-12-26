package ru.mephi.kafedra.data.entities.components

import javax.persistence.Entity
import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 26.12.16.
 */
@Entity
class TextComponent extends Component {
    @NotNull
    Integer fontSize

    @NotNull
    String fontFamily

    @NotNull
    String colorHex
    Boolean isHref = false

    Type type = Type.TEXT
}
