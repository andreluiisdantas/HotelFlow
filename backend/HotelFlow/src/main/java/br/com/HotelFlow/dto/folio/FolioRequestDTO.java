package br.com.HotelFlow.dto.folio;

import jakarta.validation.constraints.NotNull;

public record FolioRequestDTO(
        @NotNull(message = "É obrigatório informar a reserva.")
        Long bookingId,

        @NotNull(message = "É obrigatório informar o status.")
        Long statusFolioId
) {}
