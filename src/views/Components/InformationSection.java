package views.Components;

import views.MyColors;

import javax.swing.*;
import java.awt.*;

public class InformationSection extends JTextField {

    public InformationSection(String text) {
        super(text);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Arial", Font.PLAIN, 22));
        setForeground(MyColors.GREEN);
    }

    public void setColor(MyColors color) {
        setForeground(color);
    }
}
