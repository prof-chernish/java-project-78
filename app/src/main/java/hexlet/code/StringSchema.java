package hexlet.code;

import  java.util.function.Predicate;
import  java.util.List;
import  java.util.LinkedList;

public class StringSchema {
    private List<Predicate<String>> validations = new LinkedList<>();
    private boolean isRequired = false;


    public boolean isValid(Object value) {

        if (value == null) {

            return !isRequired;
        }
        
        if (!(value instanceof String)) {

            return false;
        }

        String stringValue = (String)value;

        if (stringValue.isEmpty() && isRequired) {

            return false;
        }

        
       // Predicate<String> isNotNull = str -> str != null;
        for (Predicate<String> validation: validations) {

            if (!(validation.test(stringValue))) {
                
                return false;

            }
        }
        return true;


    }

    public StringSchema required() {
        isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<String> validation = str -> str.length() >= length;
        validations.add(validation);
        return this;
    }

    public StringSchema contains(String substring) {
        StringBuilder sb = new StringBuilder(substring);
        Predicate<String> validation = str -> str.contains(sb);
        validations.add(validation);
        return this;
    }

}
