package br.com.HotelFlow.dto.statusFolio;

import jakarta.validation.constraints.NotNull;

public record StatusFolioRequestDTO(
        @NotNull(message = "É obrigatório informar o status") String status
) {}
