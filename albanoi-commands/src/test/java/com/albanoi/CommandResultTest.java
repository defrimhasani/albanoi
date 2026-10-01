package com.albanoi;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CommandResultTest {
    @Test
    void returnsTheResult() {
        var result = CommandResult.of("created");
        assertTrue(result.hasResult());
        assertEquals("created", result.getResult());
    }

    @Test
    void noResultIsTypeSafeAndEmpty() {
        CommandResult<String> result = CommandResult.noResult();
        assertFalse(result.hasResult());
        assertThrows(NoSuchElementException.class, result::getResult);
    }

    @Test
    void nullResultIsEmpty() {
        var result = CommandResult.of(null);
        assertFalse(result.hasResult());
        assertThrows(NoSuchElementException.class, result::getResult);
    }
}
