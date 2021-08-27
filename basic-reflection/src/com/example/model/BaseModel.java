package com.example.model;

import java.util.UUID;

public abstract class BaseModel {

    private final String uuid = UUID.randomUUID().toString();

    public String getUUID() {
        return uuid;
    }
}
