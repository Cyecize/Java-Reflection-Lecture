package com.example.demo.proxycommand.commands;

import com.example.demo.proxycommand.Command;
import org.springframework.stereotype.Component;

@Component
public class Exit implements Command {
    @Override
    public String execute(String... payload) {
        System.exit(0);
        return null;
    }
}
