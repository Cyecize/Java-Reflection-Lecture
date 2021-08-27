package com.example.mocklibrary;

/**
 * Example inspired from Microsoft EWS Java API
 */
public class LibraryImpl implements LibraryInterface {
    @Override
    public void methodA() {
        this.methodB();
        this.methodC();
        this.methodD();
        this.methodE();
    }

    private void methodB() {
        System.out.println("Method B");
    }

    private void methodC() {
        System.out.println("Method C");
    }

    private void methodD() {
        System.out.println("Method D");
    }

    private void methodE() {
        System.out.println("Method E");
    }
}
