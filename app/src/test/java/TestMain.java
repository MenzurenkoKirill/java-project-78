import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schemas.StringSchema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMain {

    Validator validatorTest;
    StringSchema schemaTest;

    @BeforeEach
    public void init() {
        validatorTest = new Validator();
        schemaTest = validatorTest.string();
    }

    @Test
    public void beforeRequiredTest() {
        boolean expected = true;
        boolean actual1 = schemaTest.isValid("");
        boolean actual2 = schemaTest.isValid(null);
        boolean actual3 = schemaTest.isValid(5);
        boolean actual4 = schemaTest.isValid("hexlet");
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
        assertEquals(expected, actual3);
        assertEquals(expected, actual4);
    }

    @Test
    public void afterRequiredTest() {
        schemaTest.required();
        boolean expected1 = false;
        boolean expected2 = true;
        boolean actual1 = schemaTest.isValid("");
        boolean actual2 = schemaTest.isValid(null);
        boolean actual3 = schemaTest.isValid(5);
        boolean actual4 = schemaTest.isValid("hexlet");
        assertEquals(expected1, actual1);
        assertEquals(expected1, actual2);
        assertEquals(expected1, actual3);
        assertEquals(expected2, actual4);
    }

    @Test
    public void addMinLengthTest() {
        schemaTest.required().minLength(4);
        boolean expected1 = true;
        boolean expected2 = false;
        boolean actual1 = schemaTest.isValid("hexlet");
        boolean actual2 = schemaTest.isValid("hex");
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void addContains() {
        schemaTest.required().contains("hex");
        boolean expected1 = true;
        boolean expected2 = false;
        boolean actual1 = schemaTest.isValid("hexlet");
        boolean actual2 = schemaTest.isValid("junit");
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void allConfTest() {
        schemaTest.required().minLength(4).contains("Hexlet");
        boolean expected1 = true;
        boolean expected2 = false;
        boolean actual1 = schemaTest.isValid("\n"
                + "Hexlet online programming school");
        boolean actual2 = schemaTest.isValid("Earth is a planet in the solar system");
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

}


