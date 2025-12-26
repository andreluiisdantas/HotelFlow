package br.com.HotelFlow.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "FolioTransaction")
@Getter @Setter
@NoArgsConstructor
public class FolioTransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_folio_transaction")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "folio", nullable = false)
    private FolioModel folio;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

}
