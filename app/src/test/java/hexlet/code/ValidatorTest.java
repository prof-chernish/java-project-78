package hexlet.code;

import org.junit.jupiter.api.Test;
import  java.util.Map;
import  java.util.HashMap;
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

    @Test
    void test2() {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();
        assertTrue(schema.isValid(null)); 
        assertTrue(schema.positive().isValid(null)); 
        schema.required();
        assertFalse(schema.isValid(null)); 
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid("5")); 
        assertFalse(schema.isValid(-10)); 
        assertFalse(schema.isValid(0)); 
        schema.range(5, 10);
        assertTrue(schema.isValid(5)); 
        assertTrue(schema.isValid(10)); 
        assertFalse(schema.isValid(4)); 
        assertFalse(schema.isValid(11)); 

    }
    @Test 
    void test3() {
        Validator validator = new Validator();
        MapSchema schema = validator.map();

        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap()));
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // true

        schema.sizeof(2);

        assertFalse(schema.isValid(data));  // false
        data.put("key2", "value2");
        assertTrue(schema.isValid(data)); // true

    }

}
