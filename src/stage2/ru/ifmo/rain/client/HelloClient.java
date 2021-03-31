package stage2.ru.ifmo.rain.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import stage2.ru.ifmo.rain.hello.Hello;

public class HelloClient implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        /* Need some help here !
        Get NoDefClassFoundError, but
        service works pretty well... */
        ServiceReference<Hello> helloRef = context.getServiceReference(Hello.class);
        Hello hello = context.getService(helloRef);
        hello.yell();
    }

    @Override
    public void stop(BundleContext context) throws Exception {

    }
}
