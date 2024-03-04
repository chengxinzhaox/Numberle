package views;

import javax.swing.*;
import java.awt.*;

class NumberKey extends JButton {

    private static final int ARC = 20; // 圆角弧度宽度

    public NumberKey(String text, int width, int height) {
        super(text);
        setPreferredSize(new Dimension(width, height)); // 设置单元格为正方形
        setFont(new Font("Arial", Font.PLAIN, 22)); // 设置字体样式和大小
        setBorder(new RoundedBorder(ARC, ARC)); // 设置自定义圆角边框
        setOpaque(false); // 设置组件为透明以绘制圆角
    }

    @Override
    public void setSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(getBackground().darker()); // 按钮被按下时变暗
        } else if (getModel().isRollover()) {
            g.setColor(getBackground().brighter()); // 鼠标悬浮时变亮
        } else {
            g.setColor(MyColors.GRAY);
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), ARC, ARC);
        super.paintComponent(g);
    }
}


