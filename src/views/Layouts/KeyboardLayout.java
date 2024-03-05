package views.Layouts;

import java.awt.*;

public class KeyboardLayout implements LayoutManager2 {

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
    }

    /**
     * Calculates the preferred size dimensions for the specified container, given the components it contains.
     * @param parent the container to be laid out
     * @return the preferred size of the container
     */
    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return new Dimension(700, 150);
    }

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
        int n = parent.getComponentCount();
        if (n == 0) return;

        int totalWidth = parent.getWidth();
        int gap = 10;
        int x;
        int y = 10;

        int firstRowWidth = 0;
        int secondRowWidth = 0;

        for (int i = 0; i < 10; i++) {
            firstRowWidth += parent.getComponent(i).getPreferredSize().width + gap;
        }
        for (int i = 10; i < n; i++) {
            secondRowWidth += parent.getComponent(i).getPreferredSize().width + gap;
        }

        x = (totalWidth - firstRowWidth + gap) / 2;
        for (int i = 0; i < 10; i++) {
            Component comp = parent.getComponent(i);
            Dimension d = comp.getPreferredSize();
            comp.setBounds(x, y, d.width, d.height);
            x += d.width + gap;
        }

        x = (totalWidth - secondRowWidth + gap) / 2;
        y += parent.getComponent(0).getPreferredSize().height + gap;
        for (int i = 10; i < n; i++) {
            Component comp = parent.getComponent(i);
            Dimension d = comp.getPreferredSize();
            comp.setBounds(x, y, d.width, d.height);
            x += d.width + gap;
        }
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
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
    }
}
