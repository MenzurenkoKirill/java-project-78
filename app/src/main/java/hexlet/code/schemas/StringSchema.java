package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    public final StringSchema required() {
        this.isRequired = true;
        Predicate<Object> predicateRequired = x -> x instanceof String && !((String) x).isEmpty();
        super.addPredicate(predicateRequired);
        return  this;
    }
    public final StringSchema minLength(int count) {
        Predicate<Object> predicateMinLength = x -> x.toString().length() >= count;
        super.addPredicate(predicateMinLength);
        return this;
    }
    public final  StringSchema contains(String content) {
        Predicate<Object> predicateContains = x -> x.toString().contains(content);
        super.addPredicate(predicateContains);
        return this;
    }

    @Override
    public boolean isEmptyData(Object value) {
        return !(value instanceof String) || ((String) value).isEmpty();
    }
}
