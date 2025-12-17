package br.com.HotelFlow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RoomStatus")
@Getter @Setter
@NoArgsConstructor
public class RoomStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room_status")
    private Long id;

    @Column(name = "status", nullable = false, unique = true, length = 255)
    private String status;
}
