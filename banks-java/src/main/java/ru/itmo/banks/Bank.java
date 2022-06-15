package ru.itmo.banks;

import ru.itmo.banks.information.InterestInfo;
import ru.itmo.banks.observer.Observer;
import ru.itmo.banks.observer.Subject;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Bank implements Subject {
    private final List<Client> clients;
    private final List<Observer> observers;
    private final int bankId;
    private final double cash;
    private InterestInfo interestInfo;

    public Bank(int id, double cash, InterestInfo info) {
        this.clients = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.interestInfo = info;
        this.bankId = id;
        this.cash = cash;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public int getBankId() {
        return bankId;
    }

    public double getCash() {
        return cash;
    }

    public InterestInfo getInterestInfo() {
        return interestInfo;
    }

    public void changeInterest(InterestInfo interestInfo) {
        this.interestInfo = interestInfo;
        for (Client client : this.clients) {
            client.changeInterest(interestInfo);
        }
        notice();
    }

    public void changeData(GregorianCalendar data) {
        for (Client client : this.clients) {
            client.changeData(data);
        }
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        this.clients.remove(client);
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notice() {
        for (Observer observer : this.observers) {
            observer.update(this);
        }
    }
}
