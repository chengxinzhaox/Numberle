package views.Components;

import views.MyColors;

import javax.swing.*;
import java.awt.*;

/**
 * The number cell class
 */
public class NumberKey extends JButton {

    private static final int ARC = 15;
    private Color backgroundColor = MyColors.GRAY;

    /**
     * Create a number key
     *
     * @param text   The text of the button
     * @param width  The width of the button
     * @param height The height of the button
     */
    public NumberKey(String text, int width, int height) {

        super(text);

        // Set the size of the button
        setPreferredSize(new Dimension(width, height));
        setBorder(new RoundedBorder(ARC, ARC));
        setOpaque(false);
        setForeground(MyColors.DEEP_GRAY);

        // Set the font style and size
        setFont(new Font("Arial", Font.PLAIN, 22));

    }

    /**
     * Set the color of the button
     *
     * @param color The color of the button
     */
    public void setColor(MyColors color) {
        backgroundColor = color;
        setForeground(MyColors.white);
        repaint();
    }

    /**
     * Get the color of the button
     *
     * @return The color of the button
     */
    public Color getColor() {
        return backgroundColor;
    }

    /**
     * Set the font size of the button
     *
     * @param size The size of the font
     */
    public void font(int size){
        setFont(new Font("Arial", Font.BOLD, size));
    }

    /**
     * Set the size of the button
     *
     * @param width  The width of the button
     * @param height The height of the button
     */
    @Override
    public void setSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    /**
     * Set the color of the button
     *
     * @param g The graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (getModel().isPressed()) {
            g2.setColor(backgroundColor.darker());
        } else if (getModel().isRollover()) {
            g2.setColor(backgroundColor.brighter());
        } else {
            g2.setColor(backgroundColor);
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);
        super.paintComponent(g);
    }
}


