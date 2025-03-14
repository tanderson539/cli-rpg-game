package com.tanderson.display;

import com.tanderson.command.CommandDispatcher;

public class Screen {

    private final GameFrame frame;

    public Screen(CommandDispatcher dispatcher) {
        frame = new GameFrame(dispatcher);
    }

    public GameFrame getFrame() {
        return frame;
    }
}
