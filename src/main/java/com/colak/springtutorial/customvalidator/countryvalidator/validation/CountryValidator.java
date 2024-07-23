package com.colak.springtutorial.customvalidator.countryvalidator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;

/**
 * The custom validator is a class that implements the ConstraintValidator interface.
 * It implements ConstraintValidator<ValidCountryCode, String>.
 * This means it's responsible for validating a field annotated with @CustomValidation and of type String.
 */
@Component
public class CountryValidator implements ConstraintValidator<ValidCountryCode, String> {
    private static final String[] COUNTRIES = Locale.getISOCountries();

    /**
     * This method initializes the validator. You can use it to access any annotation attributes if needed.
     */
    @Override
    public void initialize(ValidCountryCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * This method performs the actual validation logic.
     * You receive the value of the field being validated ( String in this case)
     * and a ConstraintValidatorContext for customizing validation behavior.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(COUNTRIES)
                .anyMatch(element -> element.equalsIgnoreCase(value));
    }
}
