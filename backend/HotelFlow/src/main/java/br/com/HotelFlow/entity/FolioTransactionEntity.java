package br.com.HotelFlow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "FolioTransaction")
@Getter @Setter
@NoArgsConstructor
public class FolioTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_folio_transaction")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "folio", nullable = false)
    private FolioEntity folio;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

}
