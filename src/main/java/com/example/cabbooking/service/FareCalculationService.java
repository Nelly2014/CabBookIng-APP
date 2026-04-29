
package com.example.cabbooking.service;

import org.springframework.stereotype.Service;

@Service
public class FareCalculationService {

    private static final double STANDARD_RATE = 10.0;
    private static final double COMFORT_RATE  = 15.0;
    private static final double LUXURY_RATE   = 25.0;

    public double calculateFare(int distanceKm, int typeOfCab) {
        double rate = switch (typeOfCab) {
            case 2 -> COMFORT_RATE;
            case 3 -> LUXURY_RATE;
            default -> STANDARD_RATE;
        };
        return distanceKm * rate;
    }

    public double calculateFare(int distanceKm) {
        return calculateFare(distanceKm, 1);
    }

    public double getRate(int typeOfCab) {
        return switch (typeOfCab) {
            case 2 -> COMFORT_RATE;
            case 3 -> LUXURY_RATE;
            default -> STANDARD_RATE;
        };
    }
}
