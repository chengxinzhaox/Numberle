package views.Components;

import views.MyColors;
import javax.swing.*;
import java.awt.*;

/**
 * The number cell class with mouse hover color change functionality.
 */
public class NumberKey extends JButton {

    private static final int ARC = 15;
    private Color backgroundColor = MyColors.GRAY; // Default background color
    private Color originalColor = backgroundColor; // Store the original color for resetting

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
        setBorder(new RoundedBorder(ARC, ARC)); // Assuming RoundedBorder is a defined class
        setOpaque(false);
        setForeground(MyColors.DEEP_GRAY);

        // Set the font style and size
        setFont(new Font("Montserrat", Font.BOLD, 22));

        // Add mouse listener for hover effect
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Temporarily change color on hover
                if (backgroundColor == MyColors.GRAY) {
                    backgroundColor = MyColors.HOVER_GRAY;
                }
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Reset to original color on mouse exit
                backgroundColor = originalColor;
                repaint();
            }
        });
    }

    /**
     * Set the color of the button. This method now also updates the originalColor
     * to ensure it reflects the most current base color of the button.
     *
     * @param color The new color of the button
     */
    public void setColor(Color color) {
        backgroundColor = color;
        originalColor = color; // Update the original color
        setForeground(MyColors.white);
        repaint();
    }

    /**
     * Get the current color of the button
     *
     * @return The current color of the button
     */
    public Color getColor() {
        return backgroundColor;
    }

    /**
     * Set the font size of the button
     *
     * @param size The size of the font
     */
    public void font(int size) {
        setFont(new Font("Montserrat", Font.BOLD, size));
    }

    /**
     * Override to set the size of the button
     *
     * @param width  The width of the button
     * @param height The height of the button
     */
    @Override
    public void setSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    /**
     * Paints the component with the specified color based on mouse interaction
     *
     * @param g The graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(backgroundColor); // Use the current backgroundColor
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);
        super.paintComponent(g);
    }
}
