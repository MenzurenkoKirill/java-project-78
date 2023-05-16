package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected boolean isRequired;
    private List<Predicate<Object>> predicates = new ArrayList<>();
    public abstract boolean isEmptyData(Object value);
    public final void addPredicate(Predicate<Object> o) {
        predicates.add(o);
    }
    public final boolean isValid(Object data) {
        if (!isRequired && isEmptyData(data)) {
            return true;
        }
        for (Predicate<Object> predicate : predicates) {
            if (!predicate.test(data)) {
                return false;
            }
        }
        return  true;
    }
}
