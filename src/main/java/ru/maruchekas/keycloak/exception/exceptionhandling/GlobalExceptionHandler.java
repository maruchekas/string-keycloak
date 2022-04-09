package ru.maruchekas.keycloak.exception.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import ru.maruchekas.keycloak.api.response.BadResponse;
import ru.maruchekas.keycloak.config.Constants;
import ru.maruchekas.keycloak.exception.AuthenticationDataException;
import ru.maruchekas.keycloak.exception.InvalidTokenException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    ResponseEntity<BadResponse> handleInvalidTokenException(InvalidTokenException exception) {
        BadResponse badDataResponse = new BadResponse().setError("token").setMessage(exception.getMessage());
        return new ResponseEntity<>(badDataResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<BadResponse> handleAuthenticationErrorException(AuthenticationDataException exception) {
        BadResponse badDataResponse = new BadResponse().setError("authenticate").setMessage(exception.getMessage());
        return new ResponseEntity<>(badDataResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    ResponseEntity<BadResponse> handleInvalidClientException(HttpClientErrorException.BadRequest exception) {
        BadResponse badDataResponse = new BadResponse()
                .setError("invalid client")
                .setMessage(Constants.INVALID_CLIENT.getMessage());
        return new ResponseEntity<>(badDataResponse, HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler
//    ResponseEntity<BadResponse> handleInvalidLoginPasswordException(HttpClientErrorException.Unauthorized exception) {
//        BadResponse badDataResponse = new BadResponse()
//                .setError("access denied")
//                .setMessage(Constants.INVALID_LOGIN_PASSWORD.getMessage());
//        return new ResponseEntity<>(badDataResponse, HttpStatus.FORBIDDEN);
//    }
}
