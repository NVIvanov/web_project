package ru.mephi.kafedra.data.entities.components

import javax.persistence.Entity

/**
 * @author nivanov
 * on 26.12.16.
 */
@Entity
class TextFieldComponent extends Component {
    Type type = Type.TEXTFIELD
}
