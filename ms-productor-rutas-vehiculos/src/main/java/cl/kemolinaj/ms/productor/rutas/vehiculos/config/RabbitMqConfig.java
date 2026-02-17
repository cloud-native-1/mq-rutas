package cl.kemolinaj.ms.productor.rutas.vehiculos.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String EXCHANGE = "exchange.rutas";
    public static final String KEY = "myKeyRutas";

    @Bean
    Jackson2JsonMessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }
}
