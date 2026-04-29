
package com.example.cabbooking.service;

import com.example.cabbooking.entity.Booking;
import com.example.cabbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FareCalculationService fareCalculationService;

    public Booking bookCab(String from, String to, int typeOfCab, int distanceKm) {
        double fare = fareCalculationService.calculateFare(distanceKm, typeOfCab);

        Booking booking = new Booking();
        booking.setFromLocation(from);
        booking.setToLocation(to);
        booking.setTypeOfCab(typeOfCab);
        booking.setDistanceKm(distanceKm);
        booking.setFare(fare);

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAllByOrderByIdDesc();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }
}
