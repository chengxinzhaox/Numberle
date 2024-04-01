package models;

public class InputValidator {

    /*@
        requires input != null;
        ensures \result == input.length() > 7;
     */
    public static boolean validateTooLong(String input) {
        assert input != null;
        return input.length() > 7;
    }

    /*@
        requires input != null;
        ensures \result == input.length() < 7;
     */
    public static boolean validateTooShort(String input) {
        assert input != null;
        return input.length() < 7;
    }

    /*@
        requires input != null;
     */
    public static boolean validateCharacters(String input) {
        assert input != null;
        return input.matches("[0-9+\\-*/= ]+");
    }

    /*@
        requires input != null;
     */
    public static boolean validateEqualSign(String input) {
        assert input != null;
        return input.contains("=");
    }
}
