package com.example.demo.proxycommand;

public class Context {
    private int commandsCount = 0;

    public void incrementCommandsCount() {
        this.commandsCount++;
    }

    public int getCommandsCount() {
        return this.commandsCount;
    }
}
