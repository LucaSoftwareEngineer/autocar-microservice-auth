package autocar.microservice.controllers;

import autocar.microservice.dto.RegisterRequest;
import autocar.microservice.dto.RegisterResponse;
import autocar.microservice.dto.TokenCheckResponse;
import autocar.microservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok().body(userService.registerUser(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/role")
    public ResponseEntity<String> getRole(@RequestHeader(name = "Authorization") String token) {
        try {
            return ResponseEntity.ok().body(userService.getRole(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/token/check")
    public ResponseEntity<TokenCheckResponse> tokenCheck(@RequestHeader(name = "Authorization") String token) {
        try {
            return ResponseEntity.ok().body(userService.tokenCheck(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
