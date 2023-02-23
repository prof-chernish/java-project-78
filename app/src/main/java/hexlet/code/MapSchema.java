package hexlet.code;

import  java.util.function.Predicate;
import  java.util.List;
import  java.util.Map;
import  java.util.LinkedList;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        Predicate<Object> validation = value -> value instanceof Map;
        validations.add(validation);

    }

    public MapSchema required() {
        isRequired = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> validation = value -> ((Map)value).size() == size;
        validations.add(validation);
        return this;
    }

}


