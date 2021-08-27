package com.example.demo.proxycommand;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommandExecutorService {

    private final Command command;

    private final Context context;

    public CommandExecutorService(@Qualifier("genericCommand") Command command,
                                  Context context) {
        this.command = command;
        this.context = context;
    }

    public String executeCommand(String... args) {
        String result = this.command.execute(args);
        this.context.incrementCommandsCount();
        return result;
    }
}
