package com.example.demo;

import com.example.demo.requestscope.RequestMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Ex02HttpServletProxy {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Ex02HttpServletProxy.class, args);
    }

    @Autowired
    private RequestMonitorService requestMonitorService;


    @GetMapping("/request")
    public String getRequestURl() {
        return this.requestMonitorService.determineRequestURL();
    }
}
