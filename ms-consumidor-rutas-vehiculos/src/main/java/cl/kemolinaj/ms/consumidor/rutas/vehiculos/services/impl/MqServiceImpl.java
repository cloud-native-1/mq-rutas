package cl.kemolinaj.ms.consumidor.rutas.vehiculos.services.impl;

import cl.kemolinaj.ms.consumidor.rutas.vehiculos.config.RabbitMqConfig;
import cl.kemolinaj.ms.consumidor.rutas.vehiculos.dtos.RutaRqDto;
import cl.kemolinaj.ms.consumidor.rutas.vehiculos.services.MqService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service("mqService")
public class MqServiceImpl implements MqService {
    @Value( "${path.file.system}")
    private String PATH_FILE_SYSTEM;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ObjectMapper objectMapper = new ObjectMapper();

    public MqServiceImpl (){
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME, ackMode = "MANUAL")
    @Override
    public void consumirRutas(RutaRqDto rqDto, Message message, Channel channel) throws IOException {
        String nomArchivo = LocalDateTime.now().format(FORMATTER) + ".json";
        log.info("[Service] - Mensaje recibido");
        log.info("[Service] - Creando archivo: {}", nomArchivo);
        Path dir = Path.of(PATH_FILE_SYSTEM);
        Files.createDirectories(dir);

        Path archivo = dir.resolve(nomArchivo);

        objectMapper.writeValue(archivo.toFile(), rqDto);

        log.info("[Service] - Archivo creado");

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
