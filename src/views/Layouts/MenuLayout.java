package views.Layouts;

import java.awt.*;

/**
 * A class to create a layout for the menu.
 */
public class MenuLayout implements LayoutManager2 {

    private final int maxWidth = 700;
    private final int maxHeight = 200;

    @Override
    public void addLayoutComponent(String name, Component comp) {}

    @Override
    public void removeLayoutComponent(Component comp) {}

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(maxWidth, maxHeight);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            int numComponents = parent.getComponentCount();
            if (numComponents == 0) {
                return;
            }

            int parentWidth = parent.getWidth();
            int gap = 20;
            int y = 30;
            int rowGap = 30;

            int totalWidthFirstRow = 0;
            for (int i = 0; i < Math.min(numComponents, 4); i++) {
                Component comp = parent.getComponent(i);
                totalWidthFirstRow += comp.getPreferredSize().width + (i < 3 ? gap : 0);
            }
            int startX = (parentWidth - totalWidthFirstRow) / 2;
            for (int i = 0; i < Math.min(numComponents, 4); i++) {
                Component comp = parent.getComponent(i);
                Dimension d = comp.getPreferredSize();
                comp.setBounds(startX, y, d.width, d.height);
                startX += d.width + gap;
            }

            for (int rowIndex = 1; rowIndex <= 3; rowIndex++) {
                int compIndex = 3 + rowIndex;
                if (compIndex < numComponents) {
                    Component comp = parent.getComponent(compIndex);
                    Dimension d = comp.getPreferredSize();
                    startX = (parentWidth - d.width) / 2;
                    if (rowIndex == 1) {
                        y += parent.getComponent(0).getPreferredSize().height + rowGap;
                    } else {
                        y += parent.getComponent(compIndex - 1).getPreferredSize().height + 10;
                    }
                    comp.setBounds(startX, y, d.width, d.height);
                }
            }
        }
    }


    @Override
    public void addLayoutComponent(Component comp, Object constraints) {}

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(maxWidth, maxHeight);
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
