package pl.offersjpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Nie znaleziono użytkownika o podanych parametrach")
public class UserNotFoundException extends RuntimeException {

}
