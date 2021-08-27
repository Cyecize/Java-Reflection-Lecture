package com.example.mocklibrary;

public class LibraryFactory {

    private static final LibraryInterface LIBRARY_INTERFACE = new LibraryImpl();

    public static LibraryInterface getInstance() {
        return LIBRARY_INTERFACE;
    }
}
