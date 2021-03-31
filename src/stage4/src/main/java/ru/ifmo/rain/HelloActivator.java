package ru.ifmo.rain;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Dictionary;
import java.util.Hashtable;

public class HelloActivator implements BundleActivator {
    public void start(BundleContext context) throws Exception {
        System.out.println("Enter HelloFromShell");
        Dictionary<String, String> properties = new Hashtable<>();
        properties.put("osgi.command.scope", "practice");
        properties.put("osgi.command.function", "hello");
        context.registerService(HelloCommand.class, new HelloCommand(), properties);
    }

    public void stop(BundleContext context) throws Exception {

    }
}
