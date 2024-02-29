package Models;

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
    public static String getEquation() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/equations.txt"));
            int randomIndex = ThreadLocalRandom.current().nextInt(lines.size());
            return lines.get(randomIndex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}