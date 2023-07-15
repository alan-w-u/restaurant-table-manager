package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    Table t1;

    @BeforeEach
    void runBefore() {
        t1 = new Table();
    }

    @Test
    void testConstructor() {
        assertTrue(t1.getTableOrder().isEmpty());
        assertEquals("available", t1.getAvailability());
    }
}