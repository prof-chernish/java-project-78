package hexlet.code;

import  java.util.function.Predicate;
import  java.util.List;
import  java.util.LinkedList;

public class NumberSchema extends BaseSchema {
    
    public NumberSchema required() {
        isRequired = true;
        return this;
    }

    public NumberSchema() {
        Predicate<Object> validation = value -> value instanceof Integer;
        validations.add(validation);

    }

    public NumberSchema positive() {
        Predicate<Object> validation = value -> ((Integer)value) > 0;
        validations.add(validation);
        return this;

    }

    public NumberSchema range(int a, int b) {
        Predicate<Object> validation = value -> ((Integer)value) >= a && ((Integer)value) <= b;
        validations.add(validation);
        return this;
    }

    
}
