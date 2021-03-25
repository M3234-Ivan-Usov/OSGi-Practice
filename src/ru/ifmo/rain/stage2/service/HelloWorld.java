package ru.ifmo.rain.stage2.service;

import ru.ifmo.rain.stage2.hello.Hello;

public class HelloWorld implements Hello {
    @Override
    public void yell() {
        System.out.println("Hello OSGi World!");
    }

    @Override
    public void over() {
        System.out.println("Goodbye...");
    }
}
