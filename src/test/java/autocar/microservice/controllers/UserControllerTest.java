package autocar.microservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import autocar.microservice.dto.RegisterRequest;
import autocar.microservice.dto.RegisterResponse;
import autocar.microservice.exceptions.InvalidRole;
import autocar.microservice.services.UserService;

public class UserControllerTest {

	@InjectMocks
	private UserController userController;
	
	@Mock
	UserService userService;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
	public void registerUserTest() throws InvalidRole {
		
		RegisterRequest req = new RegisterRequest();
		req.setEmail("test");
		req.setPassword("test");
		
		RegisterResponse res = new RegisterResponse();
		res.setEmail(req.getEmail());
		
		when(userService.registerUser(any(RegisterRequest.class))).thenReturn(res);
		
		assertEquals(userController.registerUser(req), ResponseEntity.ok().body(res));
	}
	
	
}
