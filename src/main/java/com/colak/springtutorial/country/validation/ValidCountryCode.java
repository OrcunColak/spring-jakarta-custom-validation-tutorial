package com.colak.springtutorial.country.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * A custom validation annotation is created by defining a new annotation.
 * This annotation specifies the validation rules that you want to apply to fields or methods in your classes.
 *
 * @Target: Defines where the annotation can be applied. In the example, it's specified for parameters.
 * @Retention: Specifies how long the annotation should be retained. RUNTIME means it will be available at runtime for validation.
 * @Constraint: Specifies the validator class responsible for implementing the validation logic
 */
@Target({ElementType.PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = CountryValidator.class)
@Documented
public @interface ValidCountryCode {

    String COUNTRY_CODE_IS_NOT_VALID = "Country code is not valid";

    String message() default COUNTRY_CODE_IS_NOT_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
