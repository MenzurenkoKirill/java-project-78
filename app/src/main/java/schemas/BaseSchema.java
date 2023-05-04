package schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected boolean isRequired;
    private List<Predicate<Object>> status = new ArrayList<>();
    public abstract boolean isInvalidData(Object value);
    public final void addPredicate(Predicate<Object> o) {
        status.add(o);
    }
    public final boolean isValid(Object data) {
        if (!isRequired && isInvalidData(data)) {
            return true;
        }
        for (Predicate<Object> predicate : status) {
            if (!predicate.test(data)) {
                return false;
            }
        }
        return  true;
    }
}
