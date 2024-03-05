package views;

import controllers.Controller;
import models.CalculationException;
import models.CharType;
import models.Model;
import models.IModel;
import views.Components.NumberCell;
import views.Components.NumberKey;
import views.Layouts.KeyboardLayout;
import views.Layouts.MenuLayout;
import views.Layouts.NumberCellLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {

    private static final int GAP = 10;

    private final IModel model;
    private final Controller controller;

    NumberCell[] cellList = new NumberCell[42];
    NumberKey[] keyList = new NumberKey[15];

    JPanel keypadPanel;
    JPanel cellPanel;
    JPanel menuPanel;

    int keyListIndex;
    int inputIndex;
    String guess = "";

    public View(Model model, Controller controller) {

        // Create the GUI
        createControls();

        keyListIndex = 0;

        // Set the model and controller
        this.model = model;
        this.controller = controller;

        // Add the view as an observer of the model
        model.addObserver(this);
        update(model, null);
    }

    public void createControls() {
        setTitle("Numberle");
        setLayout(new BorderLayout(GAP, GAP));

        initializeNumberPanel();
        initializeKeypadPanel();
        initializeMenuPanel();

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeMenuPanel() {
        menuPanel = new JPanel(new MenuLayout());

        addMenuButton(menuPanel, "REPLAY", 120, 50, MyColors.ORANGE, 15);
        addMenuButton(menuPanel, "SHOW ERROR", 120, 50, MyColors.GRAY, 15);
        addMenuButton(menuPanel, "TEST", 120, 50, MyColors.GRAY, 15);
        addMenuButton(menuPanel, "RANDOM", 120, 50, MyColors.GRAY, 15);

        add(menuPanel, BorderLayout.NORTH);
    }

    /**
     * Initialize the number panel
     */
    private void initializeNumberPanel() {
        cellPanel = new JPanel(new NumberCellLayout());
        for (int i = 0; i < 42; i++) {
            NumberCell cell = new NumberCell(60, 60);
            cellList[i] = cell;
            cellPanel.add(cell);
        }
        add(cellPanel, BorderLayout.CENTER);
    }

    /**
     * Initialize the keypad panel
     */
    private void initializeKeypadPanel() {
        keypadPanel = new JPanel(new KeyboardLayout());
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        String[] operators = {"<", "+", "-", "×", "÷", "=", "Enter"};

        for (String number : numbers) {
            addButton(keypadPanel, number, 53, 56);
        }

        for (String operator : operators) {
            int width = operator.equals("Enter") || operator.equals("<") ? 117 : 64;
            addButton(keypadPanel, operator, width, 56);
        }
        add(keypadPanel, BorderLayout.SOUTH);
    }

    private void addMenuButton(JPanel panel, String text, int width, int height, MyColors color, int fontSize) {
        NumberKey button = new NumberKey(text, width, height);
        button.setColor(color);
        button.font(fontSize);
        panel.add(button);
        button.addActionListener(e -> MenuButtonEventHolder(button));
    }

    /**
     * Add a button to the keypad panel
     *
     * @param panel the panel to add the button to
     * @param text  the text to display on the button
     * @param width the width of the button
     */
    private void addButton(JPanel panel, String text, int width, int height) {
        NumberKey button = new NumberKey(text, width, height);
        if (!(text.equals("Enter") || text.equals("<"))) {
            keyList[keyListIndex] = button;
            keyListIndex++;
        }
        panel.add(button);
        button.addActionListener(e -> keyEventHolder(button));
    }

    /**
     * Replace the operator to the model
     *
     * @param guess the guess
     * @return the guess with the operator replaced
     */
    private String replaceOperatorToModel(String guess) {
        return guess.replace("×", "*").replace("÷", "/");
    }

    /**
     * Replace the operator to the view
     *
     * @param guess the guess
     * @return the guess with the operator replaced
     */
    private String replaceOperatorToView(String guess) {
        return guess.replace("*", "×").replace("/", "÷");
    }

    /**
     * Replace the operator to the model
     *
     * @param c the character
     * @return the character with the operator replaced
     */
    private char replaceOperatorToModel(char c) {
        return c == '×' ? '*' : c == '÷' ? '/' : c;
    }

    /**
     * Handle the key event
     *
     * @param source the source of the event
     */
    private void keyEventHolder(NumberKey source) {
        String key = source.getText();
        if (key.matches("[0-9+\\-×÷=]+")) {
            if (guess.length() < 7) {
                guess += key;
                inputIndex++;
                cellList[inputIndex - 1].setText(key);
                cellList[inputIndex - 1].setForeground(MyColors.DEEP_GRAY);
            }
        }
        if (key.equals("<")) {
            if (inputIndex > 0 && !guess.isEmpty()) {
                guess = guess.substring(0, guess.length() - 1);
                inputIndex--;
                cellList[inputIndex].setText("");
            }
        }

        if (guess.length() == 7) {
            if (key.equals("Enter")) {
                try {
                    String guess = replaceOperatorToModel(this.guess);
                    if (controller.guessVerification(guess)) {
                        controller.updateUserLog(guess);
                        if (model.isWin(guess)) {
                            System.out.println("You win!");
                            model.initializeGame();
                        }
                    }
                } catch (CalculationException e) {
                    throw new RuntimeException(e);
                }
                guess = "";
                controller.printUserLog();
            }
        }
        //System.out.println("guess: " + guess + " index: " + inputIndex);
    }

    /**
     * Handle the menu button event
     *
     * @param source the source of the event
     */
    private void MenuButtonEventHolder(NumberKey source) {
        String key = source.getText();
        if (key.equals("REPLAY")) {
            rePlay();
        }
    }

    private void rePlay() {
        System.out.println("----------Replay----------");
        controller.initializeGame();
        keyListIndex = 0;
        inputIndex = 0;
        guess = "";
        updatePanel();
        reFreshKeyList();
        controller.printUserLog();
    }

    /**
     * Update the panel
     */
    private void updatePanel() {
        String[] log = model.getUserLog();
        int i = 0;

        // Update the number panel
        for (String logList : log) {
            logList = replaceOperatorToView(logList);
            for (char c : logList.toCharArray()) {

                String cellText = String.valueOf(c);

                // Set the color of the cell
                CharType type = model.checkCharType(replaceOperatorToModel(c), i % 7);
                switch (type) {
                    case GREEN -> cellList[i].setColor(MyColors.GREEN);
                    case ORANGE -> cellList[i].setColor(MyColors.ORANGE);
                    case GRAY -> cellList[i].setColor(MyColors.DEEP_GRAY);
                }

                // Replace the placeholder with an empty string
                if (cellText.equals(Messages.PLACE_HOLDER)) {
                    cellText = "";
                    cellList[i].setColor(MyColors.TINT_GRAY);
                }

                // Set the text of the cell
                cellList[i].setText(cellText);
                i++;
            }
        }

        // Update the keypad panel
        for (String logList : log) {
            logList = replaceOperatorToView(logList);
            for (char c : logList.toCharArray()) {
                String cellText = String.valueOf(c);
                CharType type = model.checkCharType(replaceOperatorToModel(c), i % 7);
                //System.out.println("Text: " + cellText + " Type: " + type + " Index: " + i % 7);
                for (NumberKey key : keyList) {
                    if (key.getText().equals(cellText)) {
                        switch (type) {
                            case GREEN -> {
                                key.setColor(MyColors.GREEN);
                            }
                            case ORANGE -> {
                                if (key.getColor() != MyColors.GREEN) {
                                    key.setColor(MyColors.ORANGE);
                                }
                            }
                            case GRAY -> {
                                if (key.getColor() != MyColors.GREEN && key.getColor() != MyColors.ORANGE) {
                                    key.setColor(MyColors.DEEP_GRAY);
                                }
                            }
                        }
                    }
                }
                i++;
            }
        }
    }

    /**
     * Refresh the key list when replay the game
     */
    private void reFreshKeyList() {
        for (NumberKey key : keyList) {
            key.setColor(MyColors.GRAY);
            key.setForeground(MyColors.DEEP_GRAY);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        updatePanel();
        System.out.println("Model updated");
        cellPanel.repaint();
        keypadPanel.repaint();
    }
}