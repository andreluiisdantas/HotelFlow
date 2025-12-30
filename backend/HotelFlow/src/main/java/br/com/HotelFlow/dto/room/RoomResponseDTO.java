package br.com.HotelFlow.dto.room;

import br.com.HotelFlow.model.RoomModel;

public record RoomResponseDTO(
        Long id,
        String roomType,
        String roomStatus,
        String doorCode
) {
    public RoomResponseDTO(RoomModel room){
        this(
                room.getId(),
                (room.getRoomType() != null) ? room.getRoomType().getType() : null,
                (room.getRoomStatus() != null) ? room.getRoomStatus().getStatus() : null,
                room.getDoorCode()
        );
    }
}
