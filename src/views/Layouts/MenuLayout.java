package views.Layouts;

import java.awt.*;

public class MenuLayout implements LayoutManager2 {

    private int maxWidth = 700;
    private int maxHeight = 100;
    private int gap = 20; // 组件间的间隔

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
        return new Dimension(maxWidth, maxHeight);
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            int ncomponents = parent.getComponentCount();
            int totalGapSize = (ncomponents - 1) * gap; // 计算总间隔大小
            int totalComponentsWidth = 0;
            for (Component comp : parent.getComponents()) {
                totalComponentsWidth += comp.getPreferredSize().width;
            }
            totalComponentsWidth += totalGapSize; // 包括间隔的总宽度

            int startX = (maxWidth - totalComponentsWidth) / 2; // 计算第一个组件的起始X坐标以实现水平居中
            int containerCenterY = maxHeight / 2; // 容器中心的Y坐标

            for (Component comp : parent.getComponents()) {
                Dimension compSize = comp.getPreferredSize();
                int compY = containerCenterY - compSize.height / 2; // 计算组件的Y坐标以实现垂直居中

                comp.setBounds(startX, compY, compSize.width, compSize.height);
                startX += compSize.width + gap; // 更新下一个组件的起始X坐标，包括间隔
            }
        }
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
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

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(maxWidth, maxHeight);
    }
}
