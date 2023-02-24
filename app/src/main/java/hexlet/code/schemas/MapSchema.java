package hexlet.code.schemas;

import  java.util.function.Predicate;
import  java.util.Map;


public final class MapSchema extends BaseSchema {

    public MapSchema() {

        Predicate<Object> validation = value -> value instanceof Map;
        validations.add(validation);
    }


    public MapSchema required() {

        isRequired = true;
        return this;
    }


    public MapSchema sizeof(int size) {

        Predicate<Object> validation = value -> ((Map) value).size() == size;
        validations.add(validation);
        return this;
    }


    public MapSchema shape(Map schemaMap) {

        Predicate<Object> validation = value -> {

            for (var key: schemaMap.keySet()) {
                var map = (Map) value;

                if (!map.containsKey(key)) {
                    return false;
                }

                BaseSchema schema = (BaseSchema) schemaMap.get(key);

                if (!schema.isValid(map.get(key))) {
                    return false;
                }
            }
            return true;
        };

        validations.add(validation);
        return this;
    }
}
