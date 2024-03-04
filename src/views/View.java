package views;

import models.GameModel;
import views.Layouts.KeyboardLayout;
import views.Layouts.NumberCellLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {

    private static final int GAP = 10;
    private final GameModel model;

    public View(GameModel model) {
        this.model = model;
        model.addObserver(this);

        createControls();
    }
    public void createControls() {
        setTitle("Numberle");
        setLayout(new BorderLayout(GAP, GAP));
        initializeNumberPanel();
        initializeKeypadPanel();
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeNumberPanel() {
        JPanel cellPanel = new JPanel(new NumberCellLayout());

        for (int i = 0; i < 42; i++) {
            NumberCell cell = new NumberCell(60, 60);
            cellPanel.add(cell);
        }
        add(cellPanel, BorderLayout.CENTER);
    }

    private void initializeKeypadPanel() {
        JPanel keypadPanel = new JPanel(new KeyboardLayout());
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        String[] operators = {"<", "+", "-", "×", "/", "=", "Enter"};

        for (String number : numbers) {
            addButton(keypadPanel, number, 53);
        }

        for (String operator : operators) {
            int width = operator.equals("Enter") || operator.equals("<") ? 117 : 64;
            addButton(keypadPanel, operator, width);
        }
        add(keypadPanel, BorderLayout.SOUTH);
    }

    private void addButton(JPanel panel, String text, int width) {
        NumberKey button = new NumberKey(text, width, 56);
        panel.add(button);
        button.addActionListener(e -> {
            NumberKey source = (NumberKey) e.getSource();
            System.out.println(source.getText());
        });
    }

    public static void main(String[] args) {
        new View(new GameModel());
    }
    @Override
    public void update(Observable o, Object arg) {

    }
}