package br.com.HotelFlow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BookingStatus")
@Getter @Setter
@NoArgsConstructor
public class BookingStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_booking_status;

    @Column(nullable = false, unique = true, length = 255)
    private String status;
}
