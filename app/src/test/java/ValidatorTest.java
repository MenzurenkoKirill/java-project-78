
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class ValidatorTest {

    @Test
    public void testStringSchema() {
        Validator validatorTest = new Validator();
        StringSchema stringSchema = validatorTest.string();
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(5));
        assertTrue(stringSchema.isValid("hexlet"));
        stringSchema.required();
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(5));
        assertTrue(stringSchema.isValid("hexlet"));
        stringSchema.minLength(4);
        assertTrue(stringSchema.isValid("hexlet"));
        assertFalse(stringSchema.isValid("hex"));
        stringSchema.contains("Hex");
        assertTrue(stringSchema.isValid("Hexlet"));
        assertFalse(stringSchema.isValid("Junit"));
        assertTrue(stringSchema.isValid("Hexlet online programming school"));
        assertFalse(stringSchema.isValid("Earth is a planet in the solar system"));
    }

    @Test
    public void  testNumberSchema() {
        Validator validatorTest = new Validator();
        NumberSchema numberSchema = validatorTest.number();
        assertTrue(numberSchema.isValid(null));
        numberSchema.required();
        assertFalse(numberSchema.isValid(null));
        numberSchema.positive();
        assertTrue(numberSchema.isValid(5));
        assertFalse(numberSchema.isValid(-1));
        assertFalse(numberSchema.isValid(0));
        numberSchema.range(1, 10);
        assertTrue(numberSchema.isValid(1));
        assertTrue(numberSchema.isValid(10));
        assertTrue(numberSchema.isValid(2));
        assertFalse(numberSchema.isValid(12));
        assertFalse(numberSchema.isValid(-6));
        assertTrue(numberSchema.isValid(7));
        assertTrue(numberSchema.isValid(4));
    }

    @Test
    public void testMapSchema() {
        Validator validatorTest = new Validator();
        MapSchema mapSchema = validatorTest.map();
        assertTrue(mapSchema.isValid(null));
        mapSchema.required();
        assertFalse(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(new HashMap<>()));
        mapSchema.sizeof(2);
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data));
        data.put("key3", "value3");
        assertFalse(mapSchema.isValid(data));
    }

    @Test
    public void testShapeOfMap() {
        Validator validatorTest = new Validator();
        MapSchema mapSchema = validatorTest.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validatorTest.string().required());
        schemas.put("age", validatorTest.number().positive());
        mapSchema.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertTrue(mapSchema.isValid(human1));
        assertTrue(mapSchema.isValid(human2));
        assertFalse(mapSchema.isValid(human3));
        assertFalse(mapSchema.isValid(human4));
    }

}


