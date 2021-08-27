package com.example.console.commands;

public class Exit implements Command {
    @Override
    public String execute(String... payload) {
        System.exit(0);
        return null;
    }
}
