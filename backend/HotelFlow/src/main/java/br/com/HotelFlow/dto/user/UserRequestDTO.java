package br.com.HotelFlow.dto.user;

import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotNull(message = "É obrigatório informar a permissão") Long roleId,
        @NotNull(message = "É obrigatório informar o nome") String name,
        @NotNull(message = "É obrigatório informar o email") String email,
        @NotNull(message = "É obrigatório informar a senha") String password
) {}
