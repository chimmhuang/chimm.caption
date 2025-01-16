package com.github.chimmhuang.caption;

import com.github.chimmhuang.caption.ui.MainWindow;
import javax.swing.SwingUtilities;

public class ChimmCaption {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
} 