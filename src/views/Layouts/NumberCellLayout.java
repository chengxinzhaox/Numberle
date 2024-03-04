package views.Layouts;

import java.awt.*;

public class NumberCellLayout implements LayoutManager2 {
    private final int rows = 7;
    private final int cols = 6;
    private final int hgap = 10;
    private final int vgap = 10;

    @Override
    public void addLayoutComponent(String name, Component comp) {
        // 不需要实现
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        // 不需要实现
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(700, 550);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        // 直接返回preferredLayoutSize，简化处理
        return preferredLayoutSize(parent);
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int ncomponents = parent.getComponentCount();
            if (ncomponents == 0) {
                return;
            }

            int totalWidth = parent.getWidth() - (insets.left + insets.right);
            int totalHeight = parent.getHeight() - (insets.top + insets.bottom);

            int maxComponentWidth = 0;
            int maxComponentHeight = 0;
            for (int i = 0; i < ncomponents; i++) {
                Component comp = parent.getComponent(i);
                Dimension d = comp.getPreferredSize();
                maxComponentWidth = Math.max(maxComponentWidth, d.width);
                maxComponentHeight = Math.max(maxComponentHeight, d.height);
            }

            int x = (totalWidth - (cols * maxComponentWidth + (cols - 1) * hgap)) / 2 + insets.left;
            int y = (totalHeight - (rows * maxComponentHeight + (rows - 1) * vgap)) / 2 + insets.top;

            for (int i = 0; i < ncomponents; i++) {
                int row = i / cols;
                int col = i % cols;

                int px = x + (maxComponentWidth + hgap) * col;
                int py = y + (maxComponentHeight + vgap) * row;

                Component comp = parent.getComponent(i);
                comp.setBounds(px, py, maxComponentWidth, maxComponentHeight);
            }
        }
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        // 不需要实现
    }

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
    public void invalidateLayout(Container target) {
        // 不需要实现
    }
}
