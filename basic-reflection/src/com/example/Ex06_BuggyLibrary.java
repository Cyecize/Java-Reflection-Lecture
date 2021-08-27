package com.example;

import com.example.mocklibrary.LibraryFactory;
import com.example.mocklibrary.LibraryInterface;

import java.lang.reflect.Method;
import java.util.List;

public class Ex06_BuggyLibrary {
    public static void main(String[] args) {
        LibraryInterface libraryInterface = LibraryFactory.getInstance();

        final List<String> methods = List.of("methodB", "methodD", "methodE");

        try {
            Class<?> cls = libraryInterface.getClass();
            for (String method : methods) {
                Method declaredMethod = cls.getDeclaredMethod(method);
                declaredMethod.setAccessible(true);

                declaredMethod.invoke(libraryInterface);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
