package cl.kemolinaj.ms.productor.rutas.vehiculos.services.impl;

import cl.kemolinaj.ms.productor.rutas.vehiculos.config.RabbitMqConfig;
import cl.kemolinaj.ms.productor.rutas.vehiculos.dtos.RutaRqDto;
import cl.kemolinaj.ms.productor.rutas.vehiculos.services.MqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service("mqService")
@RequiredArgsConstructor
public class MqServiceImpl implements MqService {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public String rutasModificar(RutaRqDto rqDto) {
        log.info("[Service] - Enviando");
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.KEY, rqDto);
        log.info("[Service] - Enviado");
        return "Mensaje enviado";
    }
}
