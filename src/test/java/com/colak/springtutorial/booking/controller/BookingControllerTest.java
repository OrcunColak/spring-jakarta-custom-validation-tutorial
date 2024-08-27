package com.colak.springtutorial.booking.controller;

import com.colak.springtutorial.booking.dto.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testValidCountryCode() {
        String url = "/bookings";

        Booking booking = new Booking();

        //End date is before start date
        booking.setStartDate(LocalDate.now());

        LocalDate endDate = LocalDate.now().minusDays(1);
        booking.setEndDate(endDate);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url, booking, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
