package views.Layouts;

import java.awt.*;

public class MenuLayout implements LayoutManager2 {

    private final int maxWidth = 700;
    private final int maxHeight = 140;

    @Override
    public void addLayoutComponent(String name, Component comp) {}

    @Override
    public void removeLayoutComponent(Component comp) {}

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        // 返回容器的固定大小
        return new Dimension(maxWidth, maxHeight);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        // 返回容器的固定大小
        return new Dimension(maxWidth, maxHeight);
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            int ncomponents = parent.getComponentCount();
            if (ncomponents == 0) {
                return;
            }

            // 第一行组件的布局
            int totalWidthFirstRow = 0;
            int gap = 20;
            for (int i = 0; i < Math.min(ncomponents, 4); i++) {
                Component comp = parent.getComponent(i);
                totalWidthFirstRow += comp.getPreferredSize().width + (i < 3 ? gap : 0); // 计算第一行总宽度，包括间隔
            }
            int startX = (maxWidth - totalWidthFirstRow) / 2; // 使第一行居中
            // 组件距离窗口最上方的间距
            int topMargin = 30;
            int y = topMargin; // 第一行的起始Y坐标，考虑顶部间距
            for (int i = 0; i < Math.min(ncomponents, 4); i++) {
                Component comp = parent.getComponent(i);
                Dimension d = comp.getPreferredSize();
                comp.setBounds(startX, y, d.width, d.height);
                startX += d.width + gap;
            }

            // 第二行组件的布局
            if (ncomponents > 4) {
                Component comp = parent.getComponent(4);
                Dimension d = comp.getPreferredSize();
                // 计算第二行组件的起始X坐标，以使其居中
                startX = (maxWidth - d.width) / 2;
                // 第二行的起始Y坐标，考虑第一行组件的高度、指定的行间距以及顶部间距
                // 第一行与第二行的间距
                int rowGap = 30;
                y += parent.getComponent(0).getPreferredSize().height + rowGap;
                comp.setBounds(startX, y, d.width, d.height);
            }
        }
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {}

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

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(maxWidth, maxHeight);
    }
}
