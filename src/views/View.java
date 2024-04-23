package views;

import controllers.Controller;
import models.CalculationException;
import models.CharType;
import models.IModel;
import models.Model;
import views.Components.InformationSection;
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
    NumberKey[] menuList = new NumberKey[4];
    InformationSection informationSection;
    InformationSection errorSection;
    InformationSection equationSection;

    JPanel keypadPanel;
    JPanel cellPanel;
    JPanel menuPanel;

    int keyListIndex;
    int menuListIndex;

    int inputIndex;

    boolean isOver = false;

    String guess = "";
    String errorInfo = "";

    public View(IModel model, Controller controller) {

        keyListIndex = 0;
        menuListIndex = 0;

        // Set the model and controller
        this.model = model;
        this.controller = controller;

        // Create the GUI
        createControls();

        // Add the view as an observer of the model
        ((Model) this.model).addObserver(this);
        update((Model) this.model, null);
    }

    /**
     * Create the GUI
     */
    private void createControls() {
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

    /**
     * Initialize the menu panel
     */
    private void initializeMenuPanel() {
        menuPanel = new JPanel(new MenuLayout());

        if (controller.getGuessTime() == 0) {
            addMenuButton(menuPanel, "REPLAY", MyColors.GRAY);
        } else {
            addMenuButton(menuPanel, "REPLAY", MyColors.ORANGE);
        }

        if (controller.isShowErrorFlag()) {
            addMenuButton(menuPanel, "SHOW ERROR", MyColors.KEY_ON);
        } else {
            addMenuButton(menuPanel, "SHOW ERROR", MyColors.KEY_OFF);
        }

        if (controller.isShowEquationFlag()) {
            addMenuButton(menuPanel, "TEST", MyColors.KEY_ON);
        } else {
            addMenuButton(menuPanel, "TEST", MyColors.KEY_OFF);
        }

        if (controller.isRandomEquationFlag()) {
            addMenuButton(menuPanel, "RANDOM", MyColors.KEY_ON);
        } else {
            addMenuButton(menuPanel, "RANDOM", MyColors.KEY_OFF);
        }

        informationSection = new InformationSection("", MyColors.DEEP_GRAY);
        errorSection = new InformationSection("", MyColors.ERROR);

        if (controller.isShowEquationFlag()) {
            equationSection = new InformationSection("Equation: " + controller.getEquation(), MyColors.DEEP_GRAY);
        } else {
            equationSection = new InformationSection("", MyColors.DEEP_GRAY);
        }
        menuPanel.add(informationSection);
        menuPanel.add(errorSection);
        menuPanel.add(equationSection);

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
            addButton(keypadPanel, number, 53);
        }

        for (String operator : operators) {
            int width = operator.equals("Enter") || operator.equals("<") ? 117 : 64;
            addButton(keypadPanel, operator, width);
        }
        add(keypadPanel, BorderLayout.SOUTH);
    }

    /**
     * Add a button to the menu panel
     *
     * @param panel the panel to add the button to
     * @param text  the text to display on the button
     * @param color the color of the button
     */
    private void addMenuButton(JPanel panel, String text, MyColors color) {
        NumberKey button = new NumberKey(text, 140, 50);
        button.setColor(color);
        button.font(15);
        panel.add(button);
        menuList[menuListIndex] = button;
        menuListIndex++;
        button.addActionListener(e -> MenuButtonEventHolder(button));
    }

    /**
     * Add a button to the keypad panel
     *
     * @param panel the panel to add the button to
     * @param text  the text to display on the button
     * @param width the width of the button
     */
    private void addButton(JPanel panel, String text, int width) {
        NumberKey button = new NumberKey(text, width, 56);
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
        if (!isOver) {
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

            if (key.equals("Enter")) {
                try {
                    String guess = replaceOperatorToModel(this.guess);
                    if (controller.guessVerification(guess)) {
                        controller.updateUserLog(guess);
                        if (controller.isWin(guess)) {
                            informationSection.setText(Messages.WIN_MESSAGE);
                            informationSection.setColor(MyColors.GREEN);
                            isOver = true;
                        } else if (controller.isOver(guess)) {
                            informationSection.setText(Messages.LOSE_MESSAGE);
                            informationSection.setColor(MyColors.ERROR);
                            isOver = true;
                        }
                    }
                } catch (CalculationException e) {
                    errorInfo = e.getMessage();
                    updateErrorSection();
                    return;
                }
                guess = "";
                errorInfo = "";
                updateErrorSection();
                updateReplayButton();
            }
        }
    }

    /**
     * Handle the menu button event
     *
     * @param source the source of the event
     */
    private void MenuButtonEventHolder(NumberKey source) {

        String key = source.getText();
        if (key.equals("REPLAY")) {
            if (controller.getGuessTime() != 0) {
                rePlay();
            }
        }

        if (key.equals("SHOW ERROR")) {
            if (controller.isShowErrorFlag()) {
                controller.setShowErrorFlag(false);
                updateErrorSection();
            } else {
                controller.setShowErrorFlag(true);
                updateErrorSection();
            }
        }
        if (key.equals("TEST")) {
            if (controller.isShowEquationFlag()) {
                controller.setShowEquationFlag(false);
                updateEquationSection();
            } else {
                controller.setShowEquationFlag(true);
                updateEquationSection();
            }
        }
        if (key.equals("RANDOM")) {
            controller.setRandomEquationFlag(!controller.isRandomEquationFlag());
            rePlay();
        }
    }

    /**
     * Replay the game
     */
    private void rePlay() {
        controller.initializeGame();
        keyListIndex = 0;
        inputIndex = 0;
        guess = "";
        isOver = false;
        updatePanel();
        reFreshKeyList();

        updateEquationSection();

        errorInfo = "";
        informationSection.setText("");

        updateErrorSection();
        updateReplayButton();
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
                for (NumberKey key : keyList) {
                    if (key.getText().equals(cellText)) {
                        switch (type) {
                            case GREEN -> key.setColor(MyColors.GREEN);
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
     * Update the menu panel
     * Re-color the menu panel
     */
    private void updateMenuPanel() {
        if (controller.isShowErrorFlag()) {
            menuList[1].setColor(MyColors.KEY_ON);
        } else {
            menuList[1].setColor(MyColors.KEY_OFF);
        }

        if (controller.isShowEquationFlag()) {
            menuList[2].setColor(MyColors.KEY_ON);
        } else {
            menuList[2].setColor(MyColors.KEY_OFF);
        }

        if (controller.isRandomEquationFlag()) {
            menuList[3].setColor(MyColors.KEY_ON);
        } else {
            menuList[3].setColor(MyColors.KEY_OFF);
        }
    }

    /**
     * Update the error section
     */
    private void updateErrorSection() {
        if (controller.isShowErrorFlag()) {
            errorSection.setText(errorInfo);
            errorSection.setColor(MyColors.ERROR);
        } else {
            errorSection.setText(errorInfo);
            errorSection.setColor(MyColors.BACKGROUND);
        }
        updateMenuPanel();
    }

    /**
     * Update the equation section
     */
    private void updateEquationSection() {
        if (controller.isShowEquationFlag()) {
            equationSection.setText("Equation: " + replaceOperatorToView(controller.getEquation()));
        } else {
            equationSection.setText("");
        }
        updateMenuPanel();
    }

    /**
     * Update the replay button
     */
    private void updateReplayButton() {
        if (controller.getGuessTime() == 0) {
            menuList[0].setColor(MyColors.GRAY);
        } else {
            menuList[0].setColor(MyColors.ORANGE);
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

    /**
     * Update the view
     *
     * @param o   the observable object
     * @param arg the object passed to the notifyObservers method
     */
    @Override
    public void update(Observable o, Object arg) {
        updatePanel();
        updateMenuPanel();
        cellPanel.repaint();
        keypadPanel.repaint();
    }
}