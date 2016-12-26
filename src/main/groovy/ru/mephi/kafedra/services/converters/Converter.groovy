package ru.mephi.kafedra.services.converters

/**
 * @author nivanov
 * on 26.12.16.
 */
interface Converter<FROM, TO> {
    TO convert(FROM dto)
}