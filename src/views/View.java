package views;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {

    private static final int GRID_ROWS = 6;
    private static final int GRID_COLS = 7;
    private static final int KEY_ROWS = 2;
    private static final int KEY_COLS = 10;
    private static final int GAP = 10; // 间隙大小

    public View() {
        setTitle("Numberle");
        setLayout(new BorderLayout(GAP, GAP));
        initializeGridPanel();
        initializeKeypadPanel();
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中显示
        setVisible(true);
    }

    private void initializeGridPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS, GAP, GAP));
        gridPanel.setPreferredSize(new Dimension(400, 500));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        NumberCell[][] gridCells = new NumberCell[GRID_ROWS][GRID_COLS];

        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                gridCells[row][col] = new NumberCell(60, 60);
                gridPanel.add(gridCells[row][col]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);
    }

    private void initializeKeypadPanel() {
        JPanel keypadPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(GAP, GAP, GAP, GAP); // 设置组件之间的间距
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        String[] operators = {"+", "-", "*", "/", "Enter", "<"};

        for (int i = 0, n = numbers.length; i < n; i++) {
            String key = numbers[i];
            NumberKey button = new NumberKey(key, 60, 60);
            gbc.gridx = i % KEY_COLS; // 设置组件的Gridx
            gbc.gridy = i / KEY_COLS; // 设置组件的Gridy

            // 如果是"Enter"键，则让其横跨两个单元格
            if (key.equals("Enter") || key.equals("<")) {
                gbc.gridwidth = 2; // 横跨两列
                button.setSize(120, 60); // 为"Enter"键设置特定大小
            } else {
                gbc.gridwidth = 1; // 默认横跨一列
            }

            keypadPanel.add(button, gbc); // 添加按钮并应用GridBagConstraints设置
            button.addActionListener(e -> {
                NumberKey source = (NumberKey) e.getSource();
                System.out.println(source.getText());
            });
        }

        add(keypadPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new View());
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}