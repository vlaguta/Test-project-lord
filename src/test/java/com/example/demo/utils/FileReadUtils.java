package com.example.demo.utils;

import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class FileReadUtils {

    public static String readJsonFileContentAsString(String filepath) {
        try {
            Path path = Paths.get(new ClassPathResource(filepath).getURI());
            return String.join("", Files.readAllLines(path));
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Unable to load content from path %s", filepath), e);
        }
    }

    public static byte[] readFileAsBytes(String filepath) {
        try {
            return new ClassPathResource(filepath).getInputStream().readAllBytes();
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Unable to load content from path %s", filepath), e);
        }
    }
}
