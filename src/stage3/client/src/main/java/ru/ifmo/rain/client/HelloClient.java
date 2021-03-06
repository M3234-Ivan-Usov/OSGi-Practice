package stage3.client.src.main.java.ru.ifmo.rain.client;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import stage3.service.src.main.java.ru.ifmo.rain.hello.Hello;

@Component
public class HelloClient {
    @Reference
    private Hello instance;

    @Activate
    void run() {
        instance.greet();
    }

    @Deactivate
    void stop() {
        instance.leave();
    }

}
