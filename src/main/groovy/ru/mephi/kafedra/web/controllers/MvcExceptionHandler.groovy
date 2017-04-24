package ru.mephi.kafedra.web.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import ru.mephi.kafedra.domain.DomainException
import ru.mephi.kafedra.web.dto.ErrorDTO

/**
 * @author nivanov
 * on 14.04.17.
 */

@ControllerAdvice
class MvcExceptionHandler {

    @Autowired
    MessageSource messageSource

    @ExceptionHandler(value = DomainException.class)
    @ResponseBody
    ErrorDTO handleServer(DomainException e, Locale locale) {
        def response = new ErrorDTO()
        response.message = messageSource.getMessage(e.message, null, locale)
        response.code = e.code.toString()
        return response
    }

}
