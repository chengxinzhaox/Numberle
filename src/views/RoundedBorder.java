package views;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class RoundedBorder extends AbstractBorder {
    private final int radiusWidth;
    private final int radiusHeight;

    public RoundedBorder(int radiusWidth, int radiusHeight) {
        this.radiusWidth = radiusWidth;
        this.radiusHeight = radiusHeight;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radiusWidth, radiusHeight);
    }
}