package com.tanderson.display;

import com.tanderson.command.CommandDispatcher;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class GameFrame {

    private JFrame frame;
    private JTextArea textArea;
    private PrintStream printStream;
    private JTextField textField;

    public GameFrame(CommandDispatcher dispatcher) {

        SwingUtilities.invokeLater(() -> {
            this.initJFrame();

            this.printStream = new PrintStream(new OutputStream() {
                @Override
                public void write(int b) {
                    textArea.append(String.valueOf((char) b));
                    textArea.setCaretPosition(textArea.getDocument().getLength());
                }
            });

            textField.addActionListener(e -> {
                String userInput = textField.getText();
                textArea.append("\n> " + userInput + "\n");
                textField.setText("");

                textArea.append(dispatcher.dispatch(userInput));
            });

            frame.setVisible(true);

            TextAreaAppender.setTextArea(textArea);

            this.textField.requestFocusInWindow();

            textArea.append("Welcome to a Simple CLI RPG Game. Type 'exit' or 'x' to quit.\n");
            textArea.append("Type 'help' for a list of commands.\n");
            textArea.append("Be aware, saving is not currently supported.\n");
        });
    }

    private void initJFrame(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("CLI RPG Game");

        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        this.textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);

        this.textField = new JTextField();
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }
}
