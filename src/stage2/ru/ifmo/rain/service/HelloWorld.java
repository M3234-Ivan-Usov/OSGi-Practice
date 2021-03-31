package stage2.ru.ifmo.rain.service;

import stage2.ru.ifmo.rain.hello.Hello;

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
