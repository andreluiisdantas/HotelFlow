package br.com.HotelFlow.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "Guest")
@Getter @Setter
@NoArgsConstructor
public class GuestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_guest")
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "rg", nullable = false, unique = true, length = 255)
    private String rg;

    @Column(name = "cpf", nullable = false, unique = true, length = 255)
    private String cpf;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_birth", nullable = false)
    private LocalDate dateBirth;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "phone", nullable = false, length = 255)
    private String phone;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;
}
