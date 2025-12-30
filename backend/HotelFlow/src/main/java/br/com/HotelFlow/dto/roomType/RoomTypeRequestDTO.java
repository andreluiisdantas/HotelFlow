package br.com.HotelFlow.dto.roomType;

import jakarta.validation.constraints.NotNull;

public record RoomTypeRequestDTO(
        @NotNull(message = "É obrigatório informar o tipo") String type,
        @NotNull(message = "É obrigatório informar a capacidade de adultos") int capacityAdults,
        @NotNull(message = "É obrigatório informar a capacidade de crianças") int capacityChildren
) {}
