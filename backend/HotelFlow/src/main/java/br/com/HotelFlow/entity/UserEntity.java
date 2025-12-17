package br.com.HotelFlow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User")
@Getter @Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_role", nullable = false)
    private RolesEntity role;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "active", nullable = false)
    private Boolean active;
}
