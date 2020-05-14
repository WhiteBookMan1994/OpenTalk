package DesignPattern;

/**
 * @author dingchenchen
 * @since 2020-04-19
 */
public class Adapter implements Target{

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
