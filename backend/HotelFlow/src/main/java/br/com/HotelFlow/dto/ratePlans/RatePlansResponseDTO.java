package br.com.HotelFlow.dto.ratePlans;

import br.com.HotelFlow.model.RatePlansModel;

import java.math.BigDecimal;

public record RatePlansResponseDTO(
        Long id,
        String roomType,
        String name,
        BigDecimal standardPrice
) {
    public RatePlansResponseDTO(RatePlansModel ratePlans){
        this(
                ratePlans.getId(),
                (ratePlans.getRoomType() != null) ? ratePlans.getRoomType().getType() : null,
                ratePlans.getName(),
                ratePlans.getStandardPrice()
        );
    }
}
