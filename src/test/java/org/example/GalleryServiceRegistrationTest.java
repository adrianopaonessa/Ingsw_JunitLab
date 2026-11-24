package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GalleryServiceRegistrationTest {

    private GalleryService galleryService;

    @BeforeEach
    void setup() {
        galleryService = new GalleryService();
    }

    @Test
    void successfulUserRegistrationTest() {
        // Test registrazione avvenuta con successo
        galleryService.register("Adriano", "Quack!");
        assertTrue(galleryService.login("Adriano", "Quack!"));
    }

    @Test
    void sameUsernameRegistrationTest() {
        // Test registrazione con stesso nome utente
        galleryService.register("Adriano", "Quack!");

        IllegalStateException usernameExistsException = assertThrows(IllegalStateException.class,
                () -> galleryService.register("Adriano", "SuperQuack!"));
        assertEquals("That username is already registered", usernameExistsException.getMessage());
    }

    //
    // Test registrazione che non rispetta i requisiti del nome utente
    //
    @Test
    void nullUsernameRegistrationTest() {
        // Nome utente nullo
        IllegalArgumentException nullUsernameException = assertThrows(IllegalArgumentException.class,
                () -> galleryService.register(null, "PWD123"));
        assertEquals("Username must contain at least 3 characters", nullUsernameException.getMessage());
    }

    @Test
    void blankUsernameRegistrationTest() {
        // Nome utente vuoto
        IllegalArgumentException blandUsernameException = assertThrows(IllegalArgumentException.class,
                () -> galleryService.register("", "PWD123"));
        assertEquals("Username must contain at least 3 characters", blandUsernameException.getMessage());
    }

    @Test
    void lessThanThreeCharsUsernameRegistrationTest() {
        // Nome utente con meno di 3 caratteri
        IllegalArgumentException lessThanThreeException = assertThrows(IllegalArgumentException.class,
                () -> galleryService.register("AP", "AlfaRomeoLover123!"));
        assertEquals("Username must contain at least 3 characters", lessThanThreeException.getMessage());
    }

    //
    // Test registrazione che non rispetta i requisiti della password.
    //
    @Test
    void nullPasswordRegistrationTest() {
        // Password nulla
        IllegalArgumentException nullPasswordException = assertThrows(IllegalArgumentException.class,
                () -> galleryService.register("Account1", null));
        assertEquals("Password must contain at least 6 characters", nullPasswordException.getMessage());
    }

    @Test
    void lessThanSixPasswordCharsRegistrationTest() {
        // Password con meno di 6 caratteri
        IllegalArgumentException lessThanSixPasswordException = assertThrows(IllegalArgumentException.class,
                () -> galleryService.register("Account1", "Duck"));
        assertEquals("Password must contain at least 6 characters", lessThanSixPasswordException.getMessage());
    }
}
