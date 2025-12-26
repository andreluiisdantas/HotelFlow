package br.com.HotelFlow.dto.booking;

import br.com.HotelFlow.model.BookingModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookingResponseDTO(
        Long id,
        Long roomId,
        String roomType,
        String guestName,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate finalDate,
        BigDecimal totalPrice,
        String status
) {
    public BookingResponseDTO(BookingModel booking){
        this(
                booking.getId(),
                booking.getRoom() != null ? booking.getRoom().getId() : null,
                (booking.getRoom() != null && booking.getRoom().getRoomType() != null ? booking.getRoom().getRoomType().getType() : "Tipo N/A"),
                booking.getGuest() != null ? booking.getGuest().getName() : "Hóspede N/A",
                booking.getStartDate(),
                booking.getFinalDate(),
                booking.getTotalPrice(),
                booking.getBookingStatus() != null ? booking.getBookingStatus().getStatus() : "Pendente"
        );
    }
}
