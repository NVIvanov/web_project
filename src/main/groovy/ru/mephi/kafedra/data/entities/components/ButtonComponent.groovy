package ru.mephi.kafedra.data.entities.components

import javax.persistence.Entity
import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 26.12.16.
 */
@Entity
class ButtonComponent extends TextComponent {
    Type type = Type.BUTTON

    @NotNull
    String jsAction
}
