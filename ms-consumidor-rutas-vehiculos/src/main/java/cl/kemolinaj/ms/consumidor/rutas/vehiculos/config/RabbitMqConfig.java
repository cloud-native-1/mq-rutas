package cl.kemolinaj.ms.consumidor.rutas.vehiculos.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String QUEUE_NAME = "rutas";

    @Bean
    Jackson2JsonMessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }
}
