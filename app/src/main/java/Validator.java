import schemas.NumberSchema;
import schemas.StringSchema;

public final class Validator {
    public StringSchema string() {
        return new StringSchema();
    }
    public NumberSchema number() {
        return new NumberSchema();
    }
}
