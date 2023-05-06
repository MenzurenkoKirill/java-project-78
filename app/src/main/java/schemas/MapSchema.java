package schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    public final MapSchema required() {
        this.isRequired = true;
        Predicate<Object> predicateRequired = x -> x instanceof Map;
        super.addPredicate(predicateRequired);
        return this;
    }
    public final MapSchema sizeOf(int sizeOfMap) {
        Predicate<Object> predicateRange = x -> ((Map<?, ?>) x).size() == sizeOfMap;
        super.addPredicate(predicateRange);
        return  this;
    }

    @Override
    public boolean isInvalidData(Object value) {
        return !(value instanceof Map) || ((Map) value).isEmpty();
    }
}
