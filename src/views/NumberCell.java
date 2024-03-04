package views;

import javax.swing.*;
import java.awt.*;

class NumberCell extends JTextField {

    // The width and height of the arc
    private static final int ARC = 20;

    public NumberCell(int weight, int height) {
        super();

        // Set the size of the cell
        setPreferredSize(new Dimension(weight, height));
        setHorizontalAlignment(JTextField.CENTER);

        // Set custom rounded border
        setBorder(new RoundedBorder(ARC, ARC));
        setOpaque(false);

        setEditable(false);

        // Set the font style and size
        setForeground(MyColors.DEEP_GRAY);
        setFont(new Font("Arial", Font.BOLD, 20));

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int offset = 1;
        // Set the color of the border
        g2d.setColor(MyColors.GRAY);
        // Set the thickness of the border
        g2d.setStroke(new BasicStroke(2));
        // Draw the border
        g2d.drawRoundRect(offset, offset, getWidth() - 1 - (offset * 2), getHeight() - 1 - (offset * 2), ARC, ARC);
    }

}