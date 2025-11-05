package autocar.microservice.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "user_password", unique = true)
    private String password;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
