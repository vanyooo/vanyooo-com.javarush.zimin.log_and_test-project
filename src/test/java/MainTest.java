import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Disabled
    @Test
    @Timeout(value = 22)
    void main() throws Exception {
        String[] args = {};
        Main.main(args);
    }
}