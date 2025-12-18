package br.com.HotelFlow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Folio")
@Getter @Setter
@NoArgsConstructor
public class FolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_folio")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking", nullable = false)
    private BookingEntity booking;

    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    private StatusFolioEntity statusFolio;
}
