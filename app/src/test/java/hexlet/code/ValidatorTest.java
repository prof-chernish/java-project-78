package hexlet.code;

import org.junit.jupiter.api.Test;
import  java.util.Map;
import  java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;


public class ValidatorTest {

    @Test
    void testStringSchema() {

        Validator validator = new Validator();
        StringSchema schema = validator.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(5));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(new HashMap()));

        assertTrue(schema.minLength(2).isValid("Hello!"));
        assertFalse(schema.isValid("H"));

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        assertFalse(schema.isValid("what does the fox say"));
    }


    @Test
    void testNumberSchema() {

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
    void testMapSchema1() {

        Validator validator = new Validator();
        MapSchema schema = validator.map();

        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeof(2);

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }


    @Test
    void testMapSchema2() {

        Validator validator = new Validator();
        MapSchema schema = validator.map();

        Map<String, BaseSchema> schemas1 = new HashMap<>();
        schemas1.put("name", validator.string().required());
        schemas1.put("age", validator.number().positive());
        schema.shape(schemas1);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(schema.isValid(human4));

        Map<String, BaseSchema> schemas2 = new HashMap<>();
        schemas2.put("mother", validator.map().shape(schemas1));
        schemas2.put("father", validator.map().shape(schemas1));
        schemas2.put("children", validator.map().sizeof(2));
        schema.shape(schemas2);

        Map<String, Object> human5 = new HashMap<>();
        human5.put("name", "Ivan");
        human5.put("age", 40);
        human5.put("mother", Map.of("name", "Mariya", "age", 65));
        human5.put("father", Map.of("name", "Sergej", "age", 70));
        human5.put("children", Map.of("name1", "Irina", "name2", "Andrej"));
        assertTrue(schema.isValid(human5));

        Map<String, Object> human6 = new HashMap<>();
        human6.put("name", "Galina");
        human6.put("age", 50);
        assertFalse(schema.isValid(human6));
    }
}
