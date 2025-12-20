package autocar.microservice.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
	
}
