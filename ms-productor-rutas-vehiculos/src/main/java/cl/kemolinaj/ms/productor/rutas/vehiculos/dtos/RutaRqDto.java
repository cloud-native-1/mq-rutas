package cl.kemolinaj.ms.productor.rutas.vehiculos.dtos;

import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.NotNull;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@RecordBuilder
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public record RutaRqDto (
        @NotNull(message = "Id Ruta no puede ser nula")
        Long rutaId,

        @NotNull(message = "Patente no puede ser nula")
        String patente,

        @NotNull(message = "Tipo de evento no puede ser nulo")
        String tipoEvento,

        @NotNull(message = "Detalle no puede ser nulo")
        String detalle,

        @NotNull(message = "Horario inicio no puede ser nulo")
        LocalDateTime horaInicio,

        @NotNull(message = "Horario fin no puede ser nulo")
        LocalDateTime horaFin,

        LocalDateTime modificado
) {}
