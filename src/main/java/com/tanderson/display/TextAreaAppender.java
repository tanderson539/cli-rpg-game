package com.tanderson.display;

import javax.swing.*;

public final class TextAreaAppender {

    private static JTextArea textArea;

    private TextAreaAppender() {}

    public static void setTextArea(JTextArea ta) {
        textArea = ta;
    }

    public static void append(String text) {
        if(textArea != null) {
            SwingUtilities.invokeLater(() -> {
                textArea.append(text);
                textArea.setCaretPosition(textArea.getDocument().getLength());
            });
        } else {
            System.out.println("[WARN] - TextArea in TextAreaAppender is null");
        }
    }

    public static void appendln(String text) {
        if(textArea != null) {
            SwingUtilities.invokeLater(() -> {
                textArea.append(text + "\n");
                textArea.setCaretPosition(textArea.getDocument().getLength());
            });
        } else {
            System.out.println("[WARN] - TextArea in TextAreaAppender is null");
        }
    }
}
