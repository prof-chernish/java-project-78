package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {
    @Test
    void test1() {
        Validator validator = new Validator();
        StringSchema schema = validator.string();
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(5));
        assertFalse(schema.isValid(""));
        assertTrue(schema.minLength(2).isValid("Hello!"));
        assertFalse(schema.isValid("H"));

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        assertFalse(schema.isValid("what does the fox say"));

    }

}
