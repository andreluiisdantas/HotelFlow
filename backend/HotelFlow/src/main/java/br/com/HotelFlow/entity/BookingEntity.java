package br.com.HotelFlow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Booking")
@Getter @Setter
@NoArgsConstructor
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room", nullable = false)
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "guest", nullable = false)
    private GuestEntity guest;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "final_date", nullable = false)
    private LocalDate finalDate;

    @ManyToOne
    @JoinColumn(name = "rate_plan", nullable = false)
    private RatePlansEntity ratePlan;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "booking_status", nullable = false)
    private BookingStatusEntity bookingStatus;

    @ManyToOne
    @JoinColumn(name = "responsible", nullable = false)
    private UserEntity responsible;

    @Column(name = "description", columnDefinition = "TEXT")
    private String dscription;
}
