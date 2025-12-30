package br.com.HotelFlow.dto.ratePlans;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RatePlansRequestDTO(
        @NotNull(message = "É obrigatório informar o tipo de quarto") Long roomTypeId,
        String name,
        BigDecimal standardPrice
) {}
