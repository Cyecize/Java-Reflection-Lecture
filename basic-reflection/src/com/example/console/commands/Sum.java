package com.example.console.commands;

import com.example.console.Autowired;

public class Sum implements Command {

    public Sum(int someRandomParam) {

    }

    @Autowired
    private Sum() {

    }

    @Override
    public String execute(String... payload) {
        if (payload.length != 2) {
            return "Invalid params";
        }

        return (Double.parseDouble(payload[0]) + Double.parseDouble(payload[1])) + "";
    }
}
