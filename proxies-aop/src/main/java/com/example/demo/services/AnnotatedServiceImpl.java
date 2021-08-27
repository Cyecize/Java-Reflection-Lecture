package com.example.demo.services;

import com.example.demo.Loggable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Loggable
public class AnnotatedServiceImpl implements AnnotatedService {

    // @Loggable
    @Transactional
    public void run() {
        System.out.println("Annotated Service run 1 called");
    }

    @Override
    public void run2() {
        System.out.println("Annotated Service run 2 called");
    }

    @Override
    public void runBoth() {
        System.out.println("Running both");
        this.run();
        this.run2();
    }
}
