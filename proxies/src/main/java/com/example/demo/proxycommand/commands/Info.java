package com.example.demo.proxycommand.commands;

import com.example.demo.proxycommand.Command;
import com.example.demo.proxycommand.Context;
import org.springframework.stereotype.Component;

@Component
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
