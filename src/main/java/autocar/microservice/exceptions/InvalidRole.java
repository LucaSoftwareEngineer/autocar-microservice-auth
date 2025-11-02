package autocar.microservice.exceptions;

public class InvalidRole extends Exception {
    public InvalidRole() {
        super("role is not valid");
    }
}
