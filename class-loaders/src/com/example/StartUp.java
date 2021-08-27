package com.example;

import com.cyecize.http.HttpCookie;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class StartUp {
    private final static String START_UP_PACKAGE_PATH = StartUp.class.getName()
            .replace(StartUp.class.getSimpleName(), "")
            .replaceAll("\\.", "/");

    public static final String WORKING_DIRECTORY = URLDecoder.decode(StartUp.class.getResource("").toString()
            .replace("file:/", "")
            .replace(START_UP_PACKAGE_PATH, ""), StandardCharsets.UTF_8);

    public static void main(String[] args) throws Exception {
        System.out.println(START_UP_PACKAGE_PATH);
        System.out.println(WORKING_DIRECTORY);

        URLClassLoader classLoader = new URLClassLoader(
                new URL[]{new URL("file:/" + WORKING_DIRECTORY + "http-api.jar")},
                Thread.currentThread().getContextClassLoader()
        );

        //Load a class that is unknown to this program
        try {
            Class<?> aClass = Class.forName("com.cyecize.http.HttpRequest");
        } catch (Exception exception) {
            System.out.println("com.cyecize.http.HttpRequest not part of this app");
        }

        Class<?> httpClass = classLoader.loadClass("com.cyecize.http.HttpRequest");
        System.out.println(httpClass.getName());


        //Create instance from a foreign class
        Class<?> cookieClass = classLoader.loadClass("com.cyecize.http.HttpCookieImpl");
        System.out.println(cookieClass.getName());
        System.out.println("Class compatible: " + HttpCookie.class.isAssignableFrom(cookieClass));
        Object httpCookieInstance = cookieClass
                .getConstructor(String.class, String.class)
                .newInstance("Content-Type", "text/html");

        System.out.println(httpCookieInstance);
        System.out.println(httpCookieInstance instanceof HttpCookie);
    }
}
