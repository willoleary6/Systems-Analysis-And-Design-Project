package ui.controller;

import javax.swing.*;

abstract class BaseFrameController implements ViewController {

    JFrame frame;

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }
}
