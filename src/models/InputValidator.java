package models;

public class InputValidator {

    /*@
        requires input != null;
        ensures \result == input.length() > 7;
     */

    /**
     * Validates the length of the input.
     *
     * @param input The input string to validate.
     * @return true if the input length is valid, false otherwise.
     */
    public static boolean validateTooLong(String input) {
        assert input != null;
        return input.length() > 7;
    }

    /*@
        requires input != null;
        ensures \result == input.length() < 7;
     */

    /**
     * Validates the length of the input.
     *
     * @param input The input string to validate.
     * @return true if the input length is valid, false otherwise.
     */
    public static boolean validateTooShort(String input) {
        assert input != null;
        return input.length() < 7;
    }

    /*@
        requires input != null;
     */

    /**
     * Validates the characters of the input.
     *
     * @param input The input string to validate.
     * @return true if the input contains only valid characters, false otherwise.
     */
    public static boolean validateCharacters(String input) {
        assert input != null;
        return input.matches("[0-9+\\-*/= ]+");
    }

    /*@
        requires input != null;
     */

    /**
     * Validates the presence of an equal sign in the input.
     *
     * @param input The input string to validate.
     * @return true if the input contains an equal sign, false otherwise.
     */
    public static boolean validateEqualSign(String input) {
        assert input != null;
        return input.contains("=");
    }
}
