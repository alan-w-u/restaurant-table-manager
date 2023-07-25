package persistence;

import model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTable(Table t, String availability, List<MenuItem> tableOrder) {
        assertEquals(availability, t.getAvailability());
        assertEquals(tableOrder, t.getTableOrder());
    }
}
