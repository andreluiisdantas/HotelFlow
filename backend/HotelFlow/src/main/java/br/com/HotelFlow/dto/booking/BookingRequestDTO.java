package br.com.HotelFlow.dto.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingRequestDTO(
        @NotNull(message = "É obrigatório informar o quarto.")
        Long roomId,

        @NotNull(message = "É obrigatório informar o hóspede.")
        Long guestId,

        @NotNull(message = "Data de início é obrigatória.")
        @FutureOrPresent(message = "A data deve ser hoje ou no futuro.")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,

        @NotNull(message = "Data final é obrigatória.")
        @Future(message = "A data final deve ser no futuro.")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate finalDate,

        @NotNull(message = "É obrigatório informar o plano de tarifa.")
        Long ratePlansId,

        Long userId,
        String description
) {}
