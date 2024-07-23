package com.colak.springtutorial.validated.controller;

import com.colak.springtutorial.validated.dto.UserDto;
import com.colak.springtutorial.validated.validationgroup.AdvancedInfo;
import com.colak.springtutorial.validated.validationgroup.BasicInfo;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/user/")
public class UserController {

    /**
     * The controller receives BindingResult
     */
    @PostMapping(path = "registerBasic", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerBasic(@RequestBody @Validated(BasicInfo.class) UserDto userDto,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping(path = "registerAdvanced", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerAdvanced(@RequestBody @Validated({BasicInfo.class, AdvancedInfo.class}) UserDto userDto,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }
        return ResponseEntity.ok("User created successfully");
    }
}
