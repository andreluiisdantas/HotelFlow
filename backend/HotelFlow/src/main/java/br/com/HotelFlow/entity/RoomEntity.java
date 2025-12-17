package br.com.HotelFlow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Room")
@Getter
@Setter
@NoArgsConstructor
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_type", nullable = false)
    private RoomTypeEntity roomType;

    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    private RoomStatusEntity roomStatus;

    @Column(name = "door_code", unique = true, nullable = false, length = 255)
    private String doorCode;
}
