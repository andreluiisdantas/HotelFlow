package br.com.HotelFlow.dto.folio;

import br.com.HotelFlow.model.FolioModel;

public record FolioResponseDTO(
        Long Id,
        String statusFolio,
        Long bookingId
) {
    public FolioResponseDTO(FolioModel folio){
        this(
                folio.getId(),
                folio.getStatusFolio().getStatus(),
                folio.getBooking().getId()
        );
    }
}
