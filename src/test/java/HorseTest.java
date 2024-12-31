import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;


class HorseTest {

    Horse horseTestOne = new Horse("TestHorse", 20, 10);
    Horse horseTestTwo = new Horse("TestHorse", 20);

    @Test
    void nullName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 20, 10)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n", "\r\n"})
    void emptyName(String name) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 20, 10),
                "Name cannot be blank."
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -100.0, -0.1})
    void NegativeNumberSpeed(double speed) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("TestHorse", speed, 10)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -100.0, -0.1})
    void NegativeNumberDistance(double distance) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("TestHorse", 20, distance)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        String name = horseTestOne.getName();
        assertEquals("TestHorse", name);
    }

    @Test
    void getSpeed() {
        double speed = horseTestOne.getSpeed();
        assertEquals(20, speed);
    }

    @Test
    void getDistance() {
        double distance = horseTestOne.getDistance();
        assertEquals(10, distance);
    }

    @Test
    void getDistanceNull() {
        double distance = horseTestTwo.getDistance();
        assertEquals(0.0, distance);
    }

    @Test
    void moveGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(Math.random() * (0.9 - 0.2) + 0.2);
            horseTestOne.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "0.5, 10", // random=0.5 expected distance=15
            "0.7, 14", // random=0.7 expected distance=24
            "0.3, 6"  // random=0.3 expected distance=6.5
    })
    void move( double randomValue, double expectedDistance) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);

            horseTestTwo.move();

            assertEquals(expectedDistance, horseTestTwo.getDistance());
        }
    }
}