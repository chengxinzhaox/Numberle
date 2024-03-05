package views.Components;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * A class to create a rounded border for a component.
 */
public class RoundedBorder extends AbstractBorder {
    private final int radiusWidth;
    private final int radiusHeight;

    /**
     * Create a rounded border
     * @param radiusWidth The width of the radius
     * @param radiusHeight The height of the radius
     */
    public RoundedBorder(int radiusWidth, int radiusHeight) {
        this.radiusWidth = radiusWidth;
        this.radiusHeight = radiusHeight;
    }

    /**
     * Paint the border of the component
     * @param c The component
     * @param g The graphics object
     * @param x The x coordinate of the component
     * @param y The y coordinate of the component
     * @param width The width of the component
     * @param height The height of the component
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radiusWidth, radiusHeight);
    }
}