package ru.mephi.kafedra.domain

import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 14.04.17.
 */
class DomainException extends RuntimeException {
    Integer code

    DomainException(@NotNull Integer code, String message) {
        super(message)
        this.code = code
    }
}
