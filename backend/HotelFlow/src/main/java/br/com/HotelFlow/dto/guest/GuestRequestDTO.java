package br.com.HotelFlow.dto.guest;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record GuestRequestDTO(
        String name,
        String rg,
        String cpf,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dateBirth,
        String address,
        String phone,
        String email
) {}
