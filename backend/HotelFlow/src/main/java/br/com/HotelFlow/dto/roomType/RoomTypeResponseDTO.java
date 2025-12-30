package br.com.HotelFlow.dto.roomType;

public record RoomTypeResponseDTO(
    Long id,
    String type,
    int capacityAdults,
    int capacityChildren
) {}
