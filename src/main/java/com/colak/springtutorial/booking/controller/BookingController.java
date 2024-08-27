package com.colak.springtutorial.booking.controller;

import com.colak.springtutorial.booking.dto.Booking;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @PostMapping
    public ResponseEntity<String> createBooking(@Valid @RequestBody Booking booking, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            ObjectError firstError = allErrors.getFirst();
            return ResponseEntity.badRequest().body("Invalid booking data: " + firstError.getDefaultMessage());
        }
        // Proceed with booking creation
        return ResponseEntity.ok("Booking created successfully");
    }
}