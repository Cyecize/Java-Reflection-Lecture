package com.example.demo;

import com.example.demo.services.AnnotatedService;
import com.example.demo.services.NonAnnotatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Autowired
    private AnnotatedService annotatedService;

    @Autowired
    private NonAnnotatedService nonAnnotatedService;

    @GetMapping("/annotated")
    @ResponseStatus(HttpStatus.OK)
    public void annotated() {
        this.annotatedService.run();
        this.annotatedService.run2();
    }

    @GetMapping("/not-annotated")
    @ResponseStatus(HttpStatus.OK)
    public void notAnnotated() {
        this.nonAnnotatedService.run();
    }

    @GetMapping("/self-reference")
    @ResponseStatus(HttpStatus.OK)
    public void selfReference() {
        this.annotatedService.runBoth();
    }
}
