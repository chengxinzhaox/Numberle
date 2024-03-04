package views.Layouts;

import java.awt.*;
import javax.swing.*;

public class KeyboardLayout implements LayoutManager2 {
    private int hgap = 10; // 水平间隙
    private int vgap = 10; // 垂直间隙

    @Override
    public void addLayoutComponent(String name, Component comp) {
        // 该方法不需要实现体，因为我们不使用名称来管理组件
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        // 移除组件时不需要特殊处理
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        // 计算并返回最佳布局大小
        return new Dimension(700, 150);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        // 返回最小布局大小
        return preferredLayoutSize(parent);
    }

    @Override
    public void layoutContainer(Container parent) {
        // 实际布局逻辑
        int n = parent.getComponentCount();
        if (n == 0) return;

        int totalWidth = parent.getWidth();
        int y = vgap; // 初始y坐标
        int x = hgap; // 初始x坐标

        // 计算第一行和第二行的宽度
        int firstRowWidth = 0;
        int secondRowWidth = 0;
        for (int i = 0; i < 10; i++) { // 假设前10个是数字
            firstRowWidth += parent.getComponent(i).getPreferredSize().width + hgap;
        }
        for (int i = 10; i < n; i++) { // 假设剩下的是操作符
            secondRowWidth += parent.getComponent(i).getPreferredSize().width + hgap;
        }

        // 居中对齐第一行
        x = (totalWidth - firstRowWidth + hgap) / 2;
        for (int i = 0; i < 10; i++) {
            Component comp = parent.getComponent(i);
            Dimension d = comp.getPreferredSize();
            comp.setBounds(x, y, d.width, d.height);
            x += d.width + hgap;
        }

        // 居中对齐第二行
        x = (totalWidth - secondRowWidth + hgap) / 2;
        y += parent.getComponent(0).getPreferredSize().height + vgap;
        for (int i = 10; i < n; i++) {
            Component comp = parent.getComponent(i);
            Dimension d = comp.getPreferredSize();
            comp.setBounds(x, y, d.width, d.height);
            x += d.width + hgap;
        }
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        // 该方法不需要实现体，因为我们不使用约束来管理组件
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
        // 当布局需要被重新计算时调用
    }
}
