package br.com.HotelFlow.dto.folioTransaction;

import java.math.BigDecimal;

public record FolioTransactionResponseDTO(
        Long id,
        Long folioId,
        BigDecimal amount
) { }
