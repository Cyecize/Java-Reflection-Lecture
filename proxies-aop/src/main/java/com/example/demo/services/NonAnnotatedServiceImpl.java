package com.example.demo.services;

import org.springframework.stereotype.Service;

@Service
public class NonAnnotatedServiceImpl implements NonAnnotatedService {
    @Override
    public void run() {
        System.out.println("non annotated service runs");
    }
}
