package hexlet.code.schemas;

import  java.util.function.Predicate;
import  java.util.List;
import  java.util.LinkedList;


public abstract class BaseSchema {

    protected List<Predicate<Object>> validations = new LinkedList<>();
    protected boolean isRequired = false;

    public boolean isValid(Object value) {

        if (value == null) {
            return !isRequired;
        }

        for (Predicate<Object> validation: validations) {

            if (!(validation.test(value))) {
                return false;
            }
        }

        return true;
    }

}
