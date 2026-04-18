package com.springboot.week2.annotations.primeNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<primeNumber, Integer> {

    @Override
    public void initialize(primeNumber constraintAnnotation) {
        String message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Consider null as valid, use @NotNull for null checks
        }
        if (value < 2) {
            return false; // Prime numbers are greater than 1
        }
        for (int i = 2; i <= Math.sqrt(value); i++) {
            if (value % i == 0) {
                return false; // Not a prime number
            }
        }
        return true; // Is a prime number
    }
}
