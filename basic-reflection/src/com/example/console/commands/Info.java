package com.example.console.commands;

import com.example.console.Context;

public class Info implements Command {

    private final Context context;

    public Info(Context context) {
        this.context = context;
    }

    @Override
    public String execute(String... payload) {
        return String.format("So far %d commands were made.", this.context.getCommandsCount());
    }
}
