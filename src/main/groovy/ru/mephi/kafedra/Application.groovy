package ru.mephi.kafedra

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import ru.mephi.kafedra.services.ComponentService
import ru.mephi.kafedra.services.StorageProperties

/**
 * @author nivanov
 * on 26.12.16.
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
class Application {
    @Autowired
    ComponentService componentService

    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }
}
