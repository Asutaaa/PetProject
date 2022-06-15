package ru.itmo.banks.observer;

public class ObserverImpl implements Observer {
    private String name;

    public ObserverImpl(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject Subject) {
        System.out.println("Bank Change Interest");
    }
}
