package ru.itmo.banks.strategy;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.information.InterestInfo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DebitCard implements Card {
    private double additionalCash;
    private final int cardId;
    private final int bankId;
    private final int clientId;
    private double cash;
    private double interest;
    private final GregorianCalendar dataDebit;

    public DebitCard(int cardId, int bankId, int clientId, InterestInfo interest, GregorianCalendar data) {
        this.interest = interest.getDebitInterest() / 365;
        this.cardId = cardId;
        this.bankId = bankId;
        this.clientId = clientId;
        this.cash = 0;
        this.dataDebit = data;
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
        this.interest = interest.getDebitInterest() / 365;
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
    public double changeData(GregorianCalendar data) {
        double difference = 0;
        while (!this.dataDebit.equals(data)) {
            this.dataDebit.add(Calendar.DAY_OF_MONTH, 1);
            if (this.dataDebit.get(GregorianCalendar.DAY_OF_MONTH) == 1) {
                difference += this.additionalCash;
                this.cash += this.additionalCash;
                this.additionalCash = 0;
            }
            this.additionalCash += this.cash * this.interest;
        }
        return -difference;
    }

    @Override
    public int getCardId() {
        return this.cardId;
    }

    @Override
    public int getClientId() {
        return this.clientId;
    }

    @Override
    public int getBankId() {
        return this.bankId;
    }

    @Override
    public double getCash() {
        return this.cash;
    }

    private void check(double money)  {
        if (this.cash - money < 0) {
            throw new BanksException("it is not possible to make a transaction");
        }
    }
}
