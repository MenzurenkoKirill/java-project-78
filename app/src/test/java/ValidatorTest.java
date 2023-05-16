
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {

    @Test
    public void testStringSchema() {
        Validator validatorTest = new Validator();
        StringSchema stringSchema = validatorTest.string();
        boolean actual1 = stringSchema.isValid("");
        boolean actual2 = stringSchema.isValid(null);
        boolean actual3 = stringSchema.isValid(5);
        boolean actual4 = stringSchema.isValid("hexlet");
        boolean expected1 = true;
        boolean expected2 = false;
        assertEquals(expected1, actual1);
        assertEquals(expected1, actual2);
        assertEquals(expected1, actual3);
        assertEquals(expected1, actual4);
        stringSchema.required();
        boolean actual5 = stringSchema.isValid("");
        boolean actual6 = stringSchema.isValid(null);
        boolean actual7 = stringSchema.isValid(5);
        boolean actual8 = stringSchema.isValid("hexlet");
        assertEquals(expected2, actual5);
        assertEquals(expected2, actual6);
        assertEquals(expected2, actual7);
        assertEquals(expected1, actual8);
        stringSchema.minLength(4);
        boolean actual9 = stringSchema.isValid("hexlet");
        boolean actual10 = stringSchema.isValid("hex");
        assertEquals(expected1, actual9);
        assertEquals(expected2, actual10);
        stringSchema.contains("Hex");
        boolean actual11 = stringSchema.isValid("Hexlet");
        boolean actual12 = stringSchema.isValid("Junit");
        assertEquals(expected1, actual11);
        assertEquals(expected2, actual12);
        boolean actual13 = stringSchema.isValid("Hexlet online programming school");
        boolean actual14 = stringSchema.isValid("Earth is a planet in the solar system");
        assertEquals(expected1, actual13);
        assertEquals(expected2, actual14);
    }

    @Test
    public void  testNumberSchema() {
        Validator validatorTest = new Validator();
        NumberSchema numberSchema = validatorTest.number();
        boolean actual1 = numberSchema.isValid(null);
        boolean expected1 = true;
        assertEquals(expected1, actual1);
        numberSchema.required();
        boolean expected2 = false;
        boolean actual2 = numberSchema.isValid(null);
        assertEquals(expected2, actual2);
        numberSchema.positive();
        boolean actual3 = numberSchema.isValid(5);
        boolean actual4 = numberSchema.isValid(-1);
        boolean actual5 = numberSchema.isValid(0);
        assertEquals(expected1, actual3);
        assertEquals(expected2, actual4);
        assertEquals(expected2, actual5);
        numberSchema.range(1, 10);
        boolean actual6 = numberSchema.isValid(1);
        boolean actual7 = numberSchema.isValid(10);
        boolean actual8 = numberSchema.isValid(2);
        boolean actual9 = numberSchema.isValid(12);
        assertEquals(expected1, actual6);
        assertEquals(expected1, actual7);
        assertEquals(expected1, actual8);
        assertEquals(expected2, actual9);
        boolean actual10 = numberSchema.isValid(-6);
        boolean actual11 = numberSchema.isValid(7);
        boolean actual12 = numberSchema.isValid(4);
        assertEquals(expected2, actual10);
        assertEquals(expected1, actual11);
        assertEquals(expected1, actual12);
    }

    @Test
    public void testMapSchema() {
        Validator validatorTest = new Validator();
        MapSchema mapSchema = validatorTest.map();
        boolean actual1 = mapSchema.isValid(null);
        boolean expected1 = true;
        assertEquals(expected1, actual1);
        mapSchema.required();
        boolean actual2 = mapSchema.isValid(null);
        boolean actual3 = mapSchema.isValid(new HashMap<>());
        boolean expected2 = false;
        assertEquals(expected2, actual2);
        assertEquals(expected1, actual3);
        mapSchema.sizeOf(2);
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        boolean actual4 = mapSchema.isValid(data);
        assertEquals(expected1, actual4);
        data.put("key3", "value3");
        boolean actual5 = mapSchema.isValid(data);
        assertEquals(expected2, actual5);
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
        boolean expected1 = true;
        boolean expected2 = false;
        boolean actual1 = mapSchema.isValid(human1);
        boolean actual2 = mapSchema.isValid(human2);
        boolean actual3 = mapSchema.isValid(human3);
        boolean actual4 = mapSchema.isValid(human4);
        assertEquals(expected1, actual1);
        assertEquals(expected1, actual2);
        assertEquals(expected2, actual3);
        assertEquals(expected2, actual4);
    }

}


