package com.colak.springtutorial.validated.controller;

import com.colak.springtutorial.validated.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testRegisterBasicSuccess() {
        String url = "/api/v1/user/registerBasic";

        UserDto userDto = new UserDto();
        userDto.setUsername("john");
        userDto.setPassword("12345678");

        HttpEntity<UserDto> request = new HttpEntity<>(userDto);

        String result = testRestTemplate.postForObject(url, request, String.class);
        assertEquals("User created successfully", result);
    }

    @Test
    void testRegisterBasicFail() {
        String url = "/api/v1/user/registerBasic";

        // Fail because validation of BasicInfo fails
        UserDto userDto = new UserDto();

        HttpEntity<UserDto> request = new HttpEntity<>(userDto);

        String result = testRestTemplate.postForObject(url, request, String.class);
        assertEquals("Validation failed", result);
    }

    @Test
    void testRegisterAdvancedSuccess() {
        String url = "/api/v1/user/registerAdvanced";

        UserDto userDto = new UserDto();
        userDto.setUsername("john");
        userDto.setPassword("12345678");
        userDto.setEmail("john@gmail.com");

        HttpEntity<UserDto> request = new HttpEntity<>(userDto);

        String result = testRestTemplate.postForObject(url, request, String.class);
        assertEquals("User created successfully", result);
    }

    @Test
    void testRegisterAdvancedFailure() {
        String url = "/api/v1/user/registerAdvanced";

        UserDto userDto = new UserDto();
        userDto.setUsername("john");
        userDto.setPassword("12345678");

        HttpEntity<UserDto> request = new HttpEntity<>(userDto);

        String result = testRestTemplate.postForObject(url, request, String.class);
        assertEquals("Validation failed", result);
    }

}
