package br.com.HotelFlow.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RoomStatus")
@Getter @Setter
@NoArgsConstructor
public class RoomStatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room_status")
    private Long id;

    @Column(name = "status", nullable = false, unique = true, length = 255)
    private String status;
}
