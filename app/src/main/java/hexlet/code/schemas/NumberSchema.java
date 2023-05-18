package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    public final NumberSchema required() {
        this.isRequired = true;
        Predicate<Object> predicateRequired = x -> x instanceof Integer;
        super.addPredicate(predicateRequired);
        return this;
    }
    public final NumberSchema positive() {
        Predicate<Object> predicatePositive = x -> x instanceof Integer && (int) x > 0;
        super.addPredicate(predicatePositive);
        return  this;
    }
    public final  NumberSchema range(int startRange, int endRange) {
        Predicate<Object> predicateRange = x -> (Integer) x >= startRange && (Integer) x <= endRange;
        super.addPredicate(predicateRange);
        return  this;
    }
    @Override
    public final boolean isEmptyData(Object value) {
        return !(value instanceof Integer);
    }
}
