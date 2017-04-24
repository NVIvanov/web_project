package ru.mephi.kafedra.web.facades.converters

/**
 * @author nivanov
 * on 24.04.17.
 */
interface Converter<DTO, MODEL> {
    MODEL convertWithExistModel(MODEL model, DTO dto)

    DTO convert(MODEL model)
}