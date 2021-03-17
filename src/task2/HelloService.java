package task2;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class HelloService implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        HelloWorld world = new HelloWorld();
        world.yell();
        context.registerService(Hello.class.getName(), world, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        ServiceReference<Hello> helloRef = context.getServiceReference(Hello.class);
        context.getService(helloRef).over();
    }
}
