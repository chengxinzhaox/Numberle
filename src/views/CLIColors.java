package views;

public enum CLIColors {
    ORANGE("\033[33m"),
    GREEN("\033[32m"),
    GRAY("\033[37m"),
    RED("\033[31m"),
    RESET("\033[0m");

    private final String code;

    CLIColors(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}