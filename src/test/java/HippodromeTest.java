import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HippodromeTest {

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
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + i,i,i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());//git remote add origin https://github.com/vanyooo/vanyooo-com.javarush.zimin.log_and_test-project

    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses){
            Mockito.verify(horse).move();
        }
//        Если тестовый метод move в классе HippodromeTest заканчивается ошибкой: "org.mockito.exceptions.base.MockitoException:
//Mockito cannot mock this class: class Horse.", нужно поменять Java до версии 21
    }

    @Test
    void getWinner() {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4, 5),
                new Horse("Ace of Spades", 2.5, 6),
                new Horse("Zephyr", 2.6, 7)

        );

        Hippodrome hippodromeTest = new Hippodrome(horses);
        String name = hippodromeTest.getWinner().getName();

        assertEquals("Zephyr", name);
    }
}