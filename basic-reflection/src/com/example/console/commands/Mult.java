package com.example.console.commands;

public class Mult implements Command {

    @Override
    public String execute(String... payload) {
        if (payload.length != 2) {
            return "Invalid params";
        }

        return (Double.parseDouble(payload[0]) * Double.parseDouble(payload[1])) + "";
    }
}
