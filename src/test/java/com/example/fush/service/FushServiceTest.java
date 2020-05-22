package com.example.fush.service;

import com.example.fush.model.Fush;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FushServiceTest {

    private FushService fushService = new FushService(null);

    @Test
    public void checkForDuplicatesTest() {
        Fush fush1 = new Fush(1L, "Fush1");
        Fush fush2 = new Fush(2L, "Fush2");
        Fush fush3 = new Fush(3L, "Fush3");
        Fush fush4 = new Fush(4L, "Fush4");
        Fush fush5 = new Fush(5L, "Fush5");

        List<Fush> fushes1 = Arrays.asList(fush1, fush2);
        List<Fush> fushes2 = Arrays.asList(fush3, fush4);
        List<Fush> fushes3 = Arrays.asList(fush1, fush5);

        assertFalse(fushService.checkForDuplicates(fushes1, fushes2));
        assertFalse(fushService.checkForDuplicates(fushes2, fushes3));
        assertTrue(fushService.checkForDuplicates(fushes1, fushes3));

    }
}
