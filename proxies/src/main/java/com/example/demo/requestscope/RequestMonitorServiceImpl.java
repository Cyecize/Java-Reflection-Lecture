package com.example.demo.requestscope;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class RequestMonitorServiceImpl implements RequestMonitorService {

    private final HttpServletRequest httpServletRequest;

    public RequestMonitorServiceImpl(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public String determineRequestURL() {
        return this.httpServletRequest.getRequestURI();
    }
}
