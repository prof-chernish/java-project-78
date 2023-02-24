## Data Validator
Data validator is a library for verifying the data correctness.
<p>Example</p>
Validator v = new Validator();<br>
StringSchema schema = v.string().contains("el");<br>
schema.isValid("Hello"); // true<br>
<br>
NumberSchema = v.number().range(0, 3);<br>
schema.isValid(0); // true<br>
schema.isValid(5); // false<br>
<br>
Map &lt;String, BaseSchema> schemas = new HashMap<>();<br>
schemas.put("name", v.string().required());<br>
schemas.put("age", v.number().positive());<br>

<p>MapSchema schema = v.map().sizeof(2).shape(schemas);</p>

Map<String, Object> human1 = new HashMap<>();<br>
human1.put("name", "Kolya");<br>
human1.put("age", 100);<br>
schema.isValid(human1); // true





### Hexlet tests and linter status:
[![Actions Status](https://github.com/prof-chernish/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/prof-chernish/java-project-78/actions)
[![Java CI](https://github.com/prof-chernish/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/prof-chernish/java-project-78/actions/workflows/main.yml)
<a href="https://codeclimate.com/github/prof-chernish/java-project-78/maintainability"><img src="https://api.codeclimate.com/v1/badges/105ebd3fbfd1523f9898/maintainability" /></a>
<a href="https://codeclimate.com/github/prof-chernish/java-project-78/test_coverage"><img src="https://api.codeclimate.com/v1/badges/105ebd3fbfd1523f9898/test_coverage" /></a>
