package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkingNotFoundExeption extends RuntimeException {

    public ParkingNotFoundExeption(String id) {
        super("Parking not found with id: " + id);
    }

}