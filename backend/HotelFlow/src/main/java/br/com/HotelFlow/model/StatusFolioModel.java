package br.com.HotelFlow.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "StatusFolio")
@Getter @Setter
@NoArgsConstructor
public class StatusFolioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status_folio")
    private Long id;

    @Column(name = "status", nullable = false, length = 255)
    private String status;

}
