package com.example;

import com.example.console.Autowired;
import com.example.console.Context;
import com.example.console.commands.Command;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex05_ConsoleApp {
    public static void main(String[] args) {
        Context context = new Context();

        //A very simplified version of Spring's dependency container
        final Map<Class<?>, Object> beans = new HashMap<>() {{
            put(Context.class, context);
        }};

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Waiting for a new command");
            String[] tokens = scanner.nextLine().split("\\s+");
            String cmd = tokens[0];

            String[] params = Arrays.stream(tokens).skip(1).toArray(String[]::new);

            try {
                Command command = getCommandInstance(beans, cmd);
                try {
                    System.out.println(command.execute(params));
                    context.incrementCommandsCount();
                } catch (RuntimeException ex) {
                    System.out.println("Something went wrong");
                    ex.printStackTrace();
                }
            } catch (CommandNotFoundException ex) {
                System.out.println("Invalid Command!");
            }
        }
    }

    private static Command getCommandInstance(Map<Class<?>, Object> beans, String cmd) {
        String packagePath = Command.class.getName().replace(Command.class.getSimpleName(), "");

        try {
            //TODO: scan all classes at runtime and filter out those who implement Command interface
            Class<?> cmdClass = Class.forName(packagePath + cmd);

            Constructor<?> constructor = Arrays.stream(cmdClass.getDeclaredConstructors())
                    .filter(ctr -> ctr.getDeclaredAnnotation(Autowired.class) != null)
                    .findFirst().orElse(null);

            if (constructor == null) {
                constructor = cmdClass.getDeclaredConstructors()[0];
            }

            constructor.setAccessible(true);

            Object[] constructorParams = new Object[constructor.getParameterCount()];
            for (int paramInd = 0; paramInd < constructorParams.length; paramInd++) {
                constructorParams[paramInd] = beans.get(constructor.getParameterTypes()[paramInd]);
            }

            return (Command) constructor.newInstance(constructorParams);
        } catch (Throwable ex) {
            throw new CommandNotFoundException();
        }
    }

    static class CommandNotFoundException extends RuntimeException {

    }
}
