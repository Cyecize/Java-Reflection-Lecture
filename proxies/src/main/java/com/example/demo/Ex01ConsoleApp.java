package com.example.demo;

import com.example.demo.proxycommand.Command;
import com.example.demo.proxycommand.CommandExecutorService;
import com.example.demo.proxycommand.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class Ex01ConsoleApp {

    public static Command selectedCommand = null;

    private static final InvocationHandler INVOCATION_HANDLER = (proxy, method, args) -> {
        if (selectedCommand == null) {
            return null;
        }

        return method.invoke(selectedCommand, args);
    };

    @Bean(name = "genericCommand")
    public Command getCommand() {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        InvocationHandler invocationHandler = INVOCATION_HANDLER;

        return (Command) Proxy.newProxyInstance(
                systemClassLoader,
                new Class[] {Command.class},
                invocationHandler
        );
    }

    @Bean
    public Context context() {
        return new Context();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Ex01ConsoleApp.class, args);

        Map<String, Command> commands = run.getBeanFactory().getBeansOfType(Command.class);
        CommandExecutorService executorService = run.getBeanFactory().getBean(CommandExecutorService.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Waiting for a new command");
            String[] tokens = scanner.nextLine().split("\\s+");
            String cmd = tokens[0].toLowerCase();

            String[] params = Arrays.stream(tokens).skip(1).toArray(String[]::new);

            if (!commands.containsKey(cmd)) {
                System.out.println("Invalid command!");
                continue;
            }

            selectedCommand = commands.get(cmd);

            try {
                System.out.println(executorService.executeCommand(params));
            } catch (RuntimeException ex) {
                System.out.println("Something went wrong");
                ex.printStackTrace();
            }
        }
    }

}
