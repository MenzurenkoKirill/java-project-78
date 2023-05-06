import schemas.NumberSchema;
import schemas.StringSchema;
import schemas.MapSchema;

public final class Validator {
    public StringSchema string() {
        return new StringSchema();
    }
    public NumberSchema number() {
        return new NumberSchema();
    }
    public MapSchema map() {
        return new MapSchema();
    }
}
