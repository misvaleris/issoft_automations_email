package my.test.shop.userData;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Parser {

    private final static String JSONPath = "src/test/resources/user.json";

    public static UserData readFromFile() {
        try {
            return new ObjectMapper().readValue(new File(JSONPath), UserData.class);
        } catch (IOException ex) {
            throw new RuntimeException("Error parse file", ex);
        }
    }
}
