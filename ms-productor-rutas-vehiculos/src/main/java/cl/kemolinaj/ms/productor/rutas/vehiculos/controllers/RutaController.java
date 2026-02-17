package cl.kemolinaj.ms.productor.rutas.vehiculos.controllers;

import cl.kemolinaj.ms.productor.rutas.vehiculos.dtos.RutaRqDto;
import cl.kemolinaj.ms.productor.rutas.vehiculos.services.MqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/cola/rutas")
@RequiredArgsConstructor
public class RutaController {
    private final MqService mqService;

    @PostMapping
    public ResponseEntity<String> rutas(
            @Valid
            @RequestBody
            RutaRqDto rqDto){
        log.info("[Controller] - Enviando mensaje a la cola de rutas");
        String respuesta = mqService.rutasModificar(rqDto);
        log.info("[Controller] - Mensaje enviado");
        return ResponseEntity.ok(respuesta);
    }
}
