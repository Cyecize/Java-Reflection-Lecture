package com.example;

import com.example.model.Account;

public class Ex01_GetClass {
    public static void main(String[] args) {

        Class<Account> cls = Account.class;
        Class<?> cls2 = new Account().getClass();
        final Class<?> cls3;
        try {
            cls3 = Class.forName("com.example.model.Account");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(cls.getName());
        System.out.println(cls.getSimpleName());

        System.out.printf("All three options have the same result, %s%n", cls == cls2 && cls2 == cls3);

        // Class<T> also has a blueprint.
        // It is a common error to call 'getClass' on a Class<T>.
        // Usually then Object is expected as a parameter and a Class<T> is passed
        System.out.println(cls.getClass().getName());
    }
}
