package com.example.demo.proxycommand.commands;

import com.example.demo.proxycommand.Command;
import org.springframework.stereotype.Component;

@Component
public class Mult implements Command {

    @Override
    public String execute(String... payload) {
        if (payload.length != 2) {
            return "Invalid params";
        }

        return (Double.parseDouble(payload[0]) * Double.parseDouble(payload[1])) + "";
    }
}
