package br.com.HotelFlow.dto.roomStatus;

import jakarta.validation.constraints.NotNull;

public record RoomStatusRequestDTO(
        @NotNull(message = "É obrigatório informar o status") String status
) {}
