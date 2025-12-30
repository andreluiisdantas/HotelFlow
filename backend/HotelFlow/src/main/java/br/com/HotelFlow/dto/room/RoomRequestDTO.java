package br.com.HotelFlow.dto.room;

public record RoomRequestDTO(
        Long roomTypeId,
        Long roomStatusId,
        String doorCode
) {}
