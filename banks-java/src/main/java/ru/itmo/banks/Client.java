package ru.itmo.banks;

import ru.itmo.banks.information.InterestInfo;
import ru.itmo.banks.observer.Observer;
import ru.itmo.banks.observer.Subject;
import ru.itmo.banks.strategy.Card;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Client implements Observer {
    private final List<Card> cards;
    private final int clientId;
    private final int bankId;
    private final String name;
    private final String fullName;
    private String address;
    private int passport;
    private boolean activation;

    public Client(int bankId, int clientId, String name, String fullName) {
        this.bankId = bankId;
        this.clientId = clientId;
        this.name = name;
        this.fullName = fullName;
        this.passport = 0;
        this.address = "";
        this.activation = false;
        this.cards = new ArrayList<>();
    }

    private void check() {
        this.activation = this.passport != 0 && !Objects.equals(this.address, "");
    }

    public void addPassport(int passport) {
        this.passport = passport;
        check();
    }

    public void addAddress(String address) {
        this.address = address;
        check();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public void changeInterest(InterestInfo interestInfo) {
        for (Card card : this.cards) {
            card.changeInterest(interestInfo);
        }
    }

    public double changeData(GregorianCalendar data) throws BanksException {
        double cash = 0;
        for (Card card : this.cards) {
            cash += card.changeData(data);
        }
        return cash;
    }


    @Override
    public void update(Subject Subject) {
        System.out.println("Bank Change Interest");
    }

    public int getClientId() {
        return clientId;
    }

    public int getBankId() {
        return bankId;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isActivation() {
        return activation;
    }
}
