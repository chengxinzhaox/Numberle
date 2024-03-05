package views;

import java.awt.Color;

public class MyColors extends Color {

    public static final MyColors GRAY = new MyColors("#dde1ec");
    public static final MyColors DEEP_GRAY = new MyColors("#5c6374");
    public static final MyColors TINT_GRAY = new MyColors("#f8faff");

    public static final MyColors GREEN = new MyColors("#60bea6");
    public static final MyColors ORANGE = new MyColors("#ea9e77");

    public MyColors(String hex) {
        super(Integer.parseInt(hex.substring(1), 16));
    }
}