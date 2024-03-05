package views.Components;

import views.MyColors;

import javax.swing.*;
import java.awt.*;

public class InformationSection extends JLabel {

    public InformationSection(String text, Color color) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 20));
        setForeground(color);
        setBackground(getBackground());
    }

    public void setOpacity(float opacity) {
        setBackground(new Color(0, 0, 0, (int) (opacity * 255)));
    }

    public void setColor(MyColors color) {
        setForeground(color);
    }
}