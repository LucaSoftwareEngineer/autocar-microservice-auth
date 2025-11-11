package autocar.microservice.services;

import autocar.microservice.config.JwtUtil;
import autocar.microservice.dto.RegisterRequest;
import autocar.microservice.dto.RegisterResponse;
import autocar.microservice.exceptions.InvalidRole;
import autocar.microservice.models.Role;
import autocar.microservice.models.User;
import autocar.microservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    String passwordCriptata = "$2a$10$2W2L2GgPOENydBwKIqUJx.pBNgJinb4IeJryLeIK9bfl2I7gV6puS";
    User user = new User(1L, "test", passwordCriptata, Role.AMMINISTRATORE);

    @Test
    public void registerUser() throws InvalidRole {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(user.getEmail());
        registerRequest.setPassword("Prova123@");
        registerRequest.setRole("AMMINISTRATORE");

        when(passwordEncoder.encode(any(String.class))).thenReturn(passwordCriptata);
        when(userRepository.save(any(User.class)))
            .thenReturn(
                new User(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole()
                )
        );

        assertEquals("test", userService.registerUser(registerRequest).getEmail());
    }

    @Test
    public void getRole() {
        when(jwtUtil.extractUsername(any(String.class))).thenReturn("test");
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(user));
        assertEquals(Role.AMMINISTRATORE.name(),userService.getRole("test"));
    }

}
