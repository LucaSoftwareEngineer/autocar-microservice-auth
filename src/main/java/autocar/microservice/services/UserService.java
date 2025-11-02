package autocar.microservice.services;

import autocar.microservice.config.JwtUtil;
import autocar.microservice.dto.RegisterRequest;
import autocar.microservice.dto.RegisterResponse;
import autocar.microservice.exceptions.InvalidRole;
import autocar.microservice.models.Role;
import autocar.microservice.models.User;
import autocar.microservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public RegisterResponse registerUser(RegisterRequest request) throws InvalidRole {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);
        if (request.getRole().equals(Role.AMMINISTRATORE.name()) || request.getRole().equals(Role.SUPER_AMMINISTRATORE.name())) {
            user.setRole(request.getRole());
        } else {
            throw new InvalidRole();
        }
        User userSave = userRepository.save(user);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setEmail(user.getEmail());
        return registerResponse;
    }

    public String getRole(String token) {
        String email = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return user.getRole();
    }

}
