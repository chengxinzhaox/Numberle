package views.Layouts;

import java.awt.*;

public class NumberCellLayout implements LayoutManager2 {

    @Override
    public void addLayoutComponent(String name, Component comp) {}

    @Override
    public void removeLayoutComponent(Component comp) {}

    /**
     * Calculates the preferred size dimensions for the specified panel given the components in the specified parent container.
     * @param parent the container to be laid out
     * @return the preferred size of the container
     */
    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(700, 450);
    }

    /**
     * Calculates the minimum size dimensions for the specified panel given the components in the specified parent container.
     * @param parent the component to be laid out
     * @return the minimum size of the container
     */
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    /**
     * Lays out the specified container.
     * @param parent the container to be laid out
     */
    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int components = parent.getComponentCount();
            if (components == 0) {
                return;
            }

            int totalWidth = parent.getWidth() - (insets.left + insets.right);
            int totalHeight = parent.getHeight() - (insets.top + insets.bottom);

            int maxComponentWidth = 0;
            int maxComponentHeight = 0;
            for (int i = 0; i < components; i++) {
                Component comp = parent.getComponent(i);
                Dimension d = comp.getPreferredSize();
                maxComponentWidth = Math.max(maxComponentWidth, d.width);
                maxComponentHeight = Math.max(maxComponentHeight, d.height);
            }

            int cols = 7;
            int hGap = 10;
            int rows = 6;
            int vGap = 10;

            int x = (totalWidth - (cols * maxComponentWidth + (cols - 1) * hGap)) / 2 + insets.left;
            int y = (totalHeight - (rows * maxComponentHeight + (rows - 1) * vGap)) / 2 + insets.top;

            for (int i = 0; i < components; i++) {
                int row = i / cols;
                int col = i % cols;

                int px = x + (maxComponentWidth + hGap) * col;
                int py = y + (maxComponentHeight + vGap) * row;

                Component comp = parent.getComponent(i);
                comp.setBounds(px, py, maxComponentWidth, maxComponentHeight);
            }
        }
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {}

    /**
     * Returns the maximum size of this component.
     * @param target the component to be laid out
     * @return the maximum size of the component
     */
    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0.5f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.5f;
    }

    @Override
    public void invalidateLayout(Container target) {}
}
