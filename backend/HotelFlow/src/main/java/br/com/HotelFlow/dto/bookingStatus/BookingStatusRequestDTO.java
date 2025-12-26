package br.com.HotelFlow.dto.bookingStatus;

import jakarta.validation.constraints.NotNull;

public record BookingStatusRequestDTO(
    @NotNull(message = "É obrigatório informar o status.")
    String status
) {}
