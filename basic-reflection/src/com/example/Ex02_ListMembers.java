package com.example;

import com.example.model.Account;
import com.example.util.PersistentModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ex02_ListMembers {
    public static void main(String[] args) {
        Class<Account> cls = Account.class;

        System.out.println("Listing public members");
        System.out.println(Arrays.stream(cls.getFields()).map(Field::getName).collect(Collectors.joining(", ")));
        System.out.println(Arrays.stream(cls.getMethods()).map(Method::getName).collect(Collectors.joining(", ")));
        System.out.println(String.format("Number of constructors: %d", cls.getConstructors().length));


        // 'getDeclared' type methods give you what's declared in that concrete class
        System.out.println("\n\nListing all members");
        System.out.println(Arrays.stream(cls.getDeclaredFields()).map(Field::getName).collect(Collectors.joining(", ")));
        System.out.println(Arrays.stream(cls.getDeclaredMethods()).map(Method::getName).collect(Collectors.joining(", ")));
        System.out.println(String.format("Number of constructors: %d", cls.getDeclaredConstructors().length));


        // Calling 'getSuperclass' will give you the parent of your class or Object if your class does not extend anything.
        // Calling it on Object will return null, a great checkpoint for a recursion.
        System.out.println("\n\nListing super hidden members");
        Class<?> clsParent = cls.getSuperclass();
        System.out.println(Arrays.stream(clsParent.getDeclaredFields()).map(Field::getName).collect(Collectors.joining(", ")));


        // Only annotations marked as 'RUNTIME' will appear
        System.out.println("\n\nListing annotations");
        System.out.println(Arrays.stream(cls.getAnnotations()).map(annotation -> annotation.annotationType().toString())
                .collect(Collectors.joining(", ")));


        System.out.println("\n\nRetrieve annotation value");
        PersistentModel persistedModel = cls.getAnnotation(PersistentModel.class);
        System.out.println(persistedModel.tableName());

        // Annotation retrieval can be done dynamically
        Optional<Annotation> maybePersistentModel = Arrays.stream(cls.getAnnotations())
                .filter(annotation -> annotation.annotationType().getName().equals("com.example.util.PersistentModel"))
                .findFirst();

        maybePersistentModel.ifPresent(annotation -> System.out.println(((PersistentModel) annotation).tableName()));
    }
}
