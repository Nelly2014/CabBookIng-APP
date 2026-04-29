
package com.example.cabbooking.controller;

import com.example.cabbooking.entity.Booking;
import com.example.cabbooking.entity.ResponseMessage;
import com.example.cabbooking.service.CabBookingService;
import com.example.cabbooking.service.FareCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cab")
@CrossOrigin(origins = "*")
public class CabBookingController {

    private final CabBookingService cabBookingService;
    private final FareCalculationService fareService;

    @Autowired
    public CabBookingController(CabBookingService cabBookingService, FareCalculationService fareService) {
        this.cabBookingService = cabBookingService;
        this.fareService = fareService;
    }

    @PostMapping("/booking")
    public ResponseEntity<ResponseMessage> bookCab(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam int typeOfCab,
            @RequestParam int distanceKm) {

        Booking booking = cabBookingService.bookCab(from, to, typeOfCab, distanceKm);

        String cabName = switch (typeOfCab) {
            case 2 -> "Comfort";
            case 3 -> "Luxury";
            default -> "Standard";
        };

        String msg = "Booking #" + booking.getId() + " confirmed — "
                + cabName + " cab from " + from + " to " + to
                + " (" + distanceKm + " km). Fare: R" + booking.getFare();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMessage(1, msg, booking));
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(cabBookingService.getAllBookings());
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cabBookingService.getBookingById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fare")
    public ResponseEntity<ResponseMessage> calculateFare(
            @RequestParam int distanceKm,
            @RequestParam(defaultValue = "1") int typeOfCab) {

        double fare = fareService.calculateFare(distanceKm, typeOfCab);
        return ResponseEntity.ok(new ResponseMessage(1,
                "Estimated fare for " + distanceKm + "km: R" + fare));
    }

    @GetMapping("/health")
    public ResponseEntity<ResponseMessage> health() {
        return ResponseEntity.ok(new ResponseMessage(1, "CabBooking API is running"));
    }
}
