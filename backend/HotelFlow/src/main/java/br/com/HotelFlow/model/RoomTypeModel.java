package br.com.HotelFlow.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RoomType")
@Getter @Setter
@NoArgsConstructor
public class RoomTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room_type")
    private Long id;

    @Column(name = "type", nullable = false, unique = true, length = 255)
    private String type;

    @Column(name = "capacity_adults", nullable = false)
    private int capacityAdults;

    @Column(name = "capacity_children", nullable = false)
    private int capacityChildren;
}
