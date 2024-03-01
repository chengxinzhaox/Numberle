package models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GetEquation {
    /**
     * Get a random equation from the file
     * @return a random equation
     */
    static String getRandomEquation() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/equations.txt"));
            int randomIndex = ThreadLocalRandom.current().nextInt(lines.size());
            return lines.get(randomIndex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String getFixedEquation(int index) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/equations.txt"));
            return lines.get(index);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}