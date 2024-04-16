package models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GetEquation {

    /*@
        requires true;
        ensures \result != null;
     */

    /**
     * Get a random equation from the file
     *
     * @return a random equation
     */
    static String getRandomEquation() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/resource/equations.txt"));
            int randomIndex = ThreadLocalRandom.current().nextInt(lines.size());
            return lines.get(randomIndex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*@
        requires true;
        ensures \result != null;
     */

    /**
     * Get a fixed equation from the file
     *
     * @return a fixed equation
     */
    static String getFixedEquation() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/resource/equations.txt"));
            return lines.get(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}