package br.com.HotelFlow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record GuestDTO(
        Long id,
        String name,
        String rg,
        String cpf,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dateBirth,
        String address,
        String phone,
        String email
) {}