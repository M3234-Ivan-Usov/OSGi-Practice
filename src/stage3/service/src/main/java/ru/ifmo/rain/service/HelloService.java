package stage3.service.src.main.java.ru.ifmo.rain.service;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import stage3.service.src.main.java.ru.ifmo.rain.hello.Hello;

@Component(immediate = true)
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
