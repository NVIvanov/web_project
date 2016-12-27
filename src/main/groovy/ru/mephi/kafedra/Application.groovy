package ru.mephi.kafedra

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import ru.mephi.kafedra.services.ComponentService

/**
 * @author nivanov
 * on 26.12.16.
 */
@SpringBootApplication
class Application {
    @Autowired
    ComponentService componentService

    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

    @Bean
    CommandLineRunner init() {
        return {
            componentService.removeComponent(6)
        }
    }
}
