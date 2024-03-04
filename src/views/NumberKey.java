package views;

import javax.swing.*;
import java.awt.*;

/**
 * The number cell class
 */
class NumberKey extends JButton {

    private static final int ARC = 15;

    /**
     * Create a number key
     * @param text The text of the button
     * @param width The width of the button
     * @param height The height of the button
     */
    public NumberKey(String text, int width, int height) {

        super(text);

        // Set the size of the button
        setPreferredSize(new Dimension(width, height));
        setBorder(new RoundedBorder(ARC, ARC));
        setOpaque(false);

        // Set the font style and size
        setFont(new Font("Arial", Font.PLAIN, 22));

    }

    /**
     * Set the size of the button
     * @param width The width of the button
     * @param height The height of the button
     */
    @Override
    public void setSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    /**
     * Set the color of the button
     * @param g The graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            // When the button is pressed, the color becomes darker
            g.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            // When the mouse is over the button, the color becomes brighter
            g.setColor(getBackground().brighter());
        } else {
            g.setColor(MyColors.GRAY);
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);
        super.paintComponent(g);
    }
}


