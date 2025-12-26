package br.com.HotelFlow.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Folio")
@Getter @Setter
@NoArgsConstructor
public class FolioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_folio")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking", nullable = false)
    private BookingModel booking;

    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    private StatusFolioModel statusFolio;
}
