package br.com.HotelFlow.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Room")
@Getter @Setter
@NoArgsConstructor
public class RoomModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_type", nullable = false)
    private RoomTypeModel roomType;

    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    private RoomStatusModel roomStatus;

    @Column(name = "door_code", unique = true, nullable = false, length = 255)
    private String doorCode;
}
