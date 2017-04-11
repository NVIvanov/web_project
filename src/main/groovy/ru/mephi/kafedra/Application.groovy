package ru.mephi.kafedra

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import ru.mephi.kafedra.services.ComponentService
import ru.mephi.kafedra.services.StorageProperties

/**
 * @author nivanov
 * on 26.12.16.
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableWebMvc
class Application extends WebMvcConfigurerAdapter {
    @Autowired
    ComponentService componentService

    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

    @Override
    void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
    }

    @Override
    void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable()
    }
}
