package br.com.HotelFlow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "RatePlans")
@Getter @Setter
@NoArgsConstructor
public class RatePlansEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_rate_plan")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_type", nullable = false)
    private RoomTypeEntity roomType;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(precision = 10, scale = 2, name = "standard_price", nullable = false)
    private BigDecimal standardPrice;
}
