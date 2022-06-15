package ru.itmo.banks.strategy;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.information.InterestInfo;

import java.util.GregorianCalendar;

public class CreditCard implements Card {
    private double additionalCash;
    private final int cardId;
    private final int bankId;
    private final int clientId;
    private double cash;
    private double interest;
    private final GregorianCalendar dataCredit;
    private double minCash;

    public CreditCard(int cardId, int bankId, int clientId, InterestInfo interest, GregorianCalendar data) {
        this.interest = interest.getCreditInterest() / 365;
        this.cardId = cardId;
        this.bankId = bankId;
        this.clientId = clientId;
        this.cash = 0;
        this.dataCredit = data;
        this.minCash = -interest.getMaxCashCreditCard();
        this.additionalCash = 0;
    }

    @Override
    public void withdrawal(double cash) {
        check(cash);
        this.cash -= cash;
    }

    @Override
    public void replenishment(double cash) {
        this.cash += cash;
    }

    @Override
    public void changeInterest(InterestInfo interest) {
        this.interest = interest.getCreditInterest() / 365;
        this.minCash = interest.getMaxCashCreditCard();
    }

    @Override
    public int getNameCard() {
        int nameCard = 3;
        return nameCard;
    }

    @Override
    public void cancellationOfTheTransaction(double cash) {
        this.cash -= cash;
    }

    @Override
    public double changeData(GregorianCalendar dataNew) {
        double difference = 0;
        while (!this.dataCredit.equals(dataNew)) {
            check(0);
            this.dataCredit.add(GregorianCalendar.DAY_OF_MONTH, 1);
            if (this.dataCredit.get(GregorianCalendar.DAY_OF_MONTH) == 1) {
                difference -= this.additionalCash;
                this.cash += this.additionalCash;
                this.additionalCash = 0;
            }
            if (this.cash < 0) {
                this.additionalCash += this.cash * this.interest;
            }
        }
        return difference;
    }

    @Override
    public int getCardId() {
        return cardId;
    }

    @Override
    public int getClientId() {
        return clientId;
    }

    @Override
    public int getBankId() {
        return bankId;
    }

    @Override
    public double getCash() {
        return cash;
    }

    private void check(double money) {
        if (this.cash - money < this.minCash) {
            throw new BanksException("Too much debt");
        }
    }
}
