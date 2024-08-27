package com.colak.springtutorial.booking.dto;

import com.colak.springtutorial.booking.validation.ValidBookingDate;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@ValidBookingDate
@Getter
@Setter
public class Booking {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

}
