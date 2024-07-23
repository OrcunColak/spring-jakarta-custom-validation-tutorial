package com.colak.springtutorial.customvalidator.countryvalidator.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CountryControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testValidCountryCode() {
        String url = "/api/v1/countries/getname/tr";
        String result = testRestTemplate.getForObject(url, String.class);
        assertEquals("tr", result);
    }

    @Test
    void testInvalidCountryCode() {
        String url = "/api/v1/countries/getname/ooo";
        ResponseEntity<ProblemDetail> response = testRestTemplate.getForEntity(url, ProblemDetail.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ProblemDetail problemDetail = response.getBody();

        assert problemDetail != null;
        assertTrue(Objects.requireNonNull(problemDetail.getDetail())
                .equalsIgnoreCase("Request validation failed")
        );
    }

}
