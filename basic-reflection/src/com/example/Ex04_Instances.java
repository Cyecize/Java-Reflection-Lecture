package com.example;

import com.example.model.Account;
import com.example.util.PersistentModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ex04_Instances {
    public static void main(String[] args) {
        Class<Account> cls = Account.class;

        try {
            Constructor<Account> constructor = cls.getConstructor();
            Account account = constructor.newInstance();

            System.out.println("Account created with default constructor: " + account.stringifyNameAndId());
        } catch (NoSuchMethodException e) {
            System.out.println("Constructor with these params was not found!");
        } catch (InvocationTargetException e) {
            System.out.println("Exception was thrown inside the constructor");
            // If you are building a library it is ofter good to rethrow the original exception
            // throw e.getTargetException();
        } catch (InstantiationException e) {
            System.out.println("What the hell happened???");
        } catch (IllegalAccessException e) {
            System.out.println("Constructor probably private!");
        }


        // Instantiating an object with private constructor and 2 arguments
        try {
            Constructor<Account> declaredConstructor = cls.getDeclaredConstructor(int.class, String.class);
            // We need this constructor to be accessible
            declaredConstructor.setAccessible(true);

            Account account = declaredConstructor.newInstance(112, "Account was hacked");
            System.out.println("Account created with 2 params constructor: " + account.stringifyNameAndId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static class Util {

        private Util() {
            throw new RuntimeException();
        }
    }
}
