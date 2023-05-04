import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schemas.NumberSchema;
import schemas.StringSchema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMain {

    Validator validatorTest;
    StringSchema stringSchema;
    NumberSchema numberSchema;

    @BeforeEach
    public void init() {
        validatorTest = new Validator();
        stringSchema = validatorTest.string();
        numberSchema = validatorTest.number();
    }

    @Test
    public void beforeRequiredTest() {
        boolean expected = true;
        boolean actual1 = stringSchema.isValid("");
        boolean actual2 = stringSchema.isValid(null);
        boolean actual3 = stringSchema.isValid(5);
        boolean actual4 = stringSchema.isValid("hexlet");
        boolean actual5 = numberSchema.isValid(null);
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
        assertEquals(expected, actual3);
        assertEquals(expected, actual4);
        assertEquals(expected, actual5);
    }

    @Test
    public void afterRequiredTest() {
        stringSchema.required();
        numberSchema.required();
        boolean expected1 = false;
        boolean expected2 = true;
        boolean actual1 = stringSchema.isValid("");
        boolean actual2 = stringSchema.isValid(null);
        boolean actual3 = stringSchema.isValid(5);
        boolean actual4 = stringSchema.isValid("hexlet");
        boolean actual5 = numberSchema.isValid(null);
        assertEquals(expected1, actual1);
        assertEquals(expected1, actual2);
        assertEquals(expected1, actual3);
        assertEquals(expected2, actual4);
        assertEquals(expected1, actual5);
    }

    @Test
    public void addMinLengthTest() {
        stringSchema.required().minLength(4);
        boolean expected1 = true;
        boolean expected2 = false;
        boolean actual1 = stringSchema.isValid("hexlet");
        boolean actual2 = stringSchema.isValid("hex");
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void addContains() {
        stringSchema.required().contains("hex");
        boolean expected1 = true;
        boolean expected2 = false;
        boolean actual1 = stringSchema.isValid("hexlet");
        boolean actual2 = stringSchema.isValid("junit");
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void allConfStringTest() {
        stringSchema.required().minLength(4).contains("Hexlet");
        boolean expected1 = true;
        boolean expected2 = false;
        boolean actual1 = stringSchema.isValid("\n"
                + "Hexlet online programming school");
        boolean actual2 = stringSchema.isValid("Earth is a planet in the solar system");
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void positiveTest() {
        numberSchema.required().positive();
        boolean expected1 = true;
        boolean expected2 = false;
        boolean actual1 = numberSchema.isValid(5);
        boolean actual2 = numberSchema.isValid(-1);
        boolean actual3 = numberSchema.isValid(0);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected2, actual3);
    }

    @Test
    public void rangeTest() {
        numberSchema.required().range(1, 10);
        boolean expected1 = true;
        boolean expected2 = false;
        boolean actual1 = numberSchema.isValid(1);
        boolean actual2 = numberSchema.isValid(10);
        boolean actual3 = numberSchema.isValid(2);
        boolean actual4 = numberSchema.isValid(12);
        assertEquals(expected1, actual1);
        assertEquals(expected1, actual2);
        assertEquals(expected1, actual3);
        assertEquals(expected2, actual4);
    }

    @Test
    public void allConfNumberTest() {
        numberSchema.required().positive().range(5, 15);
        boolean expected1 = true;
        boolean expected2 = false;
        boolean actual1 = numberSchema.isValid(-6);
        boolean actual2 = numberSchema.isValid(7);
        boolean actual3 = numberSchema.isValid(4);
        assertEquals(expected2, actual1);
        assertEquals(expected1, actual2);
        assertEquals(expected2, actual3);
    }

}


