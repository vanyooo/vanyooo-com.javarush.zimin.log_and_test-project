import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    List<Horse> horses = List.of(
            new Horse("Bucephalus", 2.4, 5),
            new Horse("Ace of Spades", 2.5, 6),
            new Horse("Zephyr", 2.6, 7),
            new Horse("Blaze", 2.7, 8),
            new Horse("Lobster", 2.8, 9),
            new Horse("Pegasus", 2.9, 3),
            new Horse("Cherry", 3, 4)
    );
    Hippodrome hippodromeTest = new Hippodrome(horses);

    @Test
    void nullHorses() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void emptyHorses() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horses1 = horses;
        List<Horse> getHorses = hippodromeTest.getHorses();
        assertEquals(horses1, getHorses);
    }

    @Test
    void move() {

    }

    @Test
    void getWinner() {
        String name = hippodromeTest.getWinner().getName();
        assertEquals("Lobster", name);
    }
}