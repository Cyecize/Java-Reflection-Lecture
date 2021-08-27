package com.example;

import com.example.model.Account;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Ex03_Invocation {
    public static void main(String[] args) {
        Class<Account> cls = Account.class;
        Account account = new Account();
        account.accountId = 987;
        account.setAccountName("Account 987");

        System.out.println("Get Account ID from field");
        try {
            Field accountIdField = cls.getDeclaredField("accountId");
            try {
                Object accountId = accountIdField.get(account);
                System.out.println(String.format("Account ID: %s. Type of the object: %s", accountId, accountId.getClass().getName()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println("\n\nGet Account Name from field");
        try {
            Field accountNameField = cls.getDeclaredField("accountName");
            accountNameField.setAccessible(true);
            try {
                Object accountName = accountNameField.get(account);
                System.out.println(String.format("Account Name: %s. Type of the object: %s", accountName, accountName.getClass().getName()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println("\n\nSet Account Name from method");
        try {
            // Methods can be filtered by name, return type and parameters
            Method setAccountNameMethod = Arrays.stream(cls.getDeclaredMethods())
                    .filter(method -> method.getReturnType() == void.class
                            && method.getParameterCount() == 1
                            && method.getParameterTypes()[0] == String.class
                            // 'isNamePresent' depends on compilation options
                            && (!method.getParameters()[0].isNamePresent() || method.getParameters()[0].getName().equals("accountName")))
                    .findFirst().orElseThrow(() -> new RuntimeException("Could not find such method!"));

            // Even though method is public, we can still call this method
            setAccountNameMethod.setAccessible(true);

            Object invocationResult = setAccountNameMethod.invoke(account, "NEW ACCOUNT NAME!");
            System.out.println(String.format("Account Name: %s. Invocation result: %s", account.getAccountName(), invocationResult));
        } catch (InvocationTargetException e) {
            // throw e.getTargetException();
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
