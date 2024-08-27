package com.colak.springtutorial.booking.validation;

import com.colak.springtutorial.booking.dto.Booking;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BookingDateValidator implements ConstraintValidator<ValidBookingDate, Booking> {

    /**
     * This method initializes the validator. You can use it to access any annotation attributes if needed.
     */
    @Override
    public void initialize(ValidBookingDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * This method performs the actual validation logic.
     * You receive the value of the field being validated ( Booking in this case)
     * and a ConstraintValidatorContext for customizing validation behavior.
     */
    @Override
    public boolean isValid(Booking booking, ConstraintValidatorContext context) {
        if (booking.getStartDate() == null || booking.getEndDate() == null) {
            return true; // Let @NotNull handle null checks
        }
        return booking.getEndDate().isAfter(booking.getStartDate());
    }
}
