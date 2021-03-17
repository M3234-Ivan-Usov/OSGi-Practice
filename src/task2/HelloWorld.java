package task2;

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
