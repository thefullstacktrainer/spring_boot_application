package com.example.demo;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.example.gamerzone.model.Gamer;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GamerValidationTest {

    private final Validator validator = new LocalValidatorFactoryBean();

    @Test
    public void testValidGamer() {
        Gamer gamer = new Gamer(1L, "ValidUsername", "valid@example.com", 25);
        Set<ConstraintViolation<Gamer>> violations = validator.validate(gamer);
        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidGamer() {
        Gamer gamer = new Gamer(1L, "", "invalid-email", -1);
        Set<ConstraintViolation<Gamer>> violations = validator.validate(gamer);
        assertEquals(3, violations.size()); // Expecting violations for username, email, and age
    }
}
