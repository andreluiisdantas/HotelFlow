package br.com.HotelFlow.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Booking")
@Getter @Setter
@NoArgsConstructor
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room", nullable = false)
    private RoomModel room;

    @ManyToOne
    @JoinColumn(name = "guest", nullable = false)
    private GuestModel guest;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "final_date", nullable = false)
    private LocalDate finalDate;

    @ManyToOne
    @JoinColumn(name = "rate_plan", nullable = false)
    private RatePlansModel ratePlan;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "booking_status", nullable = false)
    private BookingStatusModel bookingStatus;

    @ManyToOne
    @JoinColumn(name = "responsible", nullable = false)
    private UserModel responsible;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
