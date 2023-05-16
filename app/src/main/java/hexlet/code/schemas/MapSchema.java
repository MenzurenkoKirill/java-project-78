package hexlet.code.schemas;

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
    public boolean isEmptyData(Object value) {
        return !(value instanceof Map);
    }
    public final MapSchema shape(Map<String, BaseSchema> schema) {
        Predicate<Object> predicateShape = x -> validationOfMap(schema, (Map<?, ?>) x);
        super.addPredicate(predicateShape);
        return this;
    }

    private boolean validationOfMap(Map<String, BaseSchema> schemas, Map<?, ?> map) {
        for (Map.Entry<String, BaseSchema> entry: schemas.entrySet()) {
            String key = entry.getKey();
            if (!map.containsKey(key) || !entry.getValue().isValid(map.get(key))) {
                return false;
            }
        }
        return true;
    }
}
