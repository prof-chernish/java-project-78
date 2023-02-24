package hexlet.code.schemas;

import  java.util.function.Predicate;


public final class StringSchema extends BaseSchema {

    public StringSchema() {

        Predicate<Object> validation = value -> value instanceof String;
        validations.add(validation);
    }


    public StringSchema required() {

        isRequired = true;
        Predicate<Object> validation = value -> !((String) value).isEmpty();
        validations.add(validation);
        return this;
    }


    public StringSchema minLength(int length) {

        Predicate<Object> validation = value -> ((String) value).length() >= length;
        validations.add(validation);
        return this;
    }


    public StringSchema contains(String substring) {

        StringBuilder sb = new StringBuilder(substring);
        Predicate<Object> validation = value -> ((String) value).contains(sb);
        validations.add(validation);
        return this;
    }

}
