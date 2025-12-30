package br.com.HotelFlow.dto.roles;

import jakarta.validation.constraints.NotNull;

public record RolesRequestDTO(
        @NotNull(message = "É obrigatório indicar a regra") String name
) {}
