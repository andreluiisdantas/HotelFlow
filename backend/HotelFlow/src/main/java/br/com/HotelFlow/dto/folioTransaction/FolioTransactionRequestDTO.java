package br.com.HotelFlow.dto.folioTransaction;

import jakarta.validation.constraints.NotNull;

public record FolioTransactionRequestDTO(
     @NotNull(message = "É obrigatório informar a transação")
     Long folioId


) { }
