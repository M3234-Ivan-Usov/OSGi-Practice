package ru.ifmo.rain.service;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import ru.ifmo.rain.hello.Hello;

@Component
public class HelloService implements Hello {
    @Activate
    @Override
    public void greet() {
        System.out.println("Hello OSGi World!");
    }

    @Deactivate
    @Override
    public void leave() {
        System.out.println("Leaving OSGi World...");
    }
}
