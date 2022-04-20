package my.test.auto.utils;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import my.test.auto.userData.UserData;

import java.io.File;
import java.io.IOException;

public class Parser {

    private final static String JSONPath = "src/test/resources/user.json";

    public static UserData readFromFile() {
        try {
            return new ObjectMapper().readValue(new File(JSONPath), UserData.class);
        } catch (IOException e) {
           throw new RuntimeException("Error parse file");
        }
    }
}
