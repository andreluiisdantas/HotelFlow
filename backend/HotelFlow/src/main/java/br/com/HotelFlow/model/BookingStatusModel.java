package br.com.HotelFlow.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BookingStatus")
@Getter @Setter
@NoArgsConstructor
public class BookingStatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking_status")
    private Long id;

    @Column(name = "status", nullable = false, unique = true, length = 255)
    private String status;
}
