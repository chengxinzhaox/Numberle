package Models;

public class InputValidator {

    /**
     * Validates the length of the input.
     * @param input The input string to validate.
     * @return true if the input length is valid, false otherwise.
     */
    public static boolean validateLength(String input) {
        return input.length() == 7;
    }

    /**
     * Validates the characters of the input.
     * @param input The input string to validate.
     * @return true if the input contains only valid characters, false otherwise.
     */
    public static boolean validateCharacters(String input) {
        return input.matches("[0-9+\\-*/= ]+");
    }

    /**
     * Validates the presence of an equal sign in the input.
     * @param input The input string to validate.
     * @return true if the input contains an equal sign, false otherwise.
     */
    public static boolean validateEqualSign(String input) {
        return input.contains("=");
    }
}
