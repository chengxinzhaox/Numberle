package test;

import models.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModelTest {

    @BeforeEach
    public void setUp() {
        Model model = new Model();
        model.initializeGame();
    }

    @Test
    public void testIsOver() {
        Model model = new Model();
        model.initializeGame();
        assertFalse(model.isOver("12345678"));
    }
}