package views.Components;

import views.MyColors;

import javax.swing.*;
import java.awt.*;

/**
 * The number cell class
 */
public class NumberCell extends JTextField {

    // The width and height of the arc
    private static final int ARC = 15;

    /**
     * Create a number cell
     * @param weight The width of the cell
     * @param height The height of the cell
     */
    public NumberCell(int weight, int height) {
        super();

        // Set the size of the cell
        setPreferredSize(new Dimension(weight, height));
        setHorizontalAlignment(JTextField.CENTER);

        setBackground(MyColors.TINT_GRAY);

        // Set custom rounded border
        setBorder(new RoundedBorder(ARC, ARC));
        setOpaque(false);

        setEditable(false);

        // Set the font style and size
        setForeground(MyColors.DEEP_GRAY);
        setFont(new Font("Arial", Font.BOLD, 20));

    }

    /**
     *  Set the color of the cell
     * @param Color The color of the cell
     */
    public void setColor(MyColors Color) {
        setBackground(Color);
        setForeground(MyColors.white);
    }

    /**
     * Set the color of the cell
     * @param g The graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);
        super.paintComponent(g);
    }
}