package cl.kemolinaj.ms.consumidor.rutas.vehiculos.services;

import cl.kemolinaj.ms.consumidor.rutas.vehiculos.dtos.RutaRqDto;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

public interface MqService {
    void consumirRutas(RutaRqDto rqDto, Message message, Channel channel) throws IOException;
}
