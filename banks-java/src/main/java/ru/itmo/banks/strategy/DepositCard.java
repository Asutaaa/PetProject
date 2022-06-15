package ru.itmo.banks.strategy;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.information.DepositInterestInfo;
import ru.itmo.banks.information.InterestInfo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class DepositCard implements Card {
    private double additionalCash;
    private final int cardId;
    private final int bankId;
    private final int clientId;
    private double cash;
    private List<DepositInterestInfo> listInterest;
    private final GregorianCalendar dataDeposit;
    private boolean activation;
    private double interest;
    private final GregorianCalendar fixDate;

    public DepositCard(int cardId, int bankId, int clientId, InterestInfo interest, GregorianCalendar data) {
        this.listInterest = interest.getDepositInterest();
        this.cardId = cardId;
        this.bankId = bankId;
        this.clientId = clientId;
        this.cash = 0;
        this.dataDeposit = data;
        System.out.println(data.getTime());
        this.additionalCash = 0;
        this.activation = false;
        this.fixDate = interest.getFixDataDepositCard();
        checkInterest();
    }

    private void checkInterest() {
        for (DepositInterestInfo item : listInterest) {
            if (item.getMaxCash() > cash && item.getMinCash() < cash) {
                interest = item.getInterest() / 365;
            }
        }
    }

    private void checkData() {
        if (this.dataDeposit.equals(fixDate)) {
            this.activation = true;
        }
    }

    @Override
    public void withdrawal(double cash) {
        if (!this.activation) {
            throw new BanksException("the card is not available for withdrawal");
        }
        if (this.cash - cash < 0) {
            throw new BanksException("it is not possible to make a transaction");
        }
        this.cash -= cash;
        checkInterest();
    }

    @Override
    public void replenishment(double cash) {
        this.cash += cash;
        checkInterest();
    }

    @Override
    public void changeInterest(InterestInfo interest) {
        this.listInterest = interest.getDepositInterest();
    }

    @Override
    public int getNameCard() {
        return 0;
    }

    @Override
    public void cancellationOfTheTransaction(double cash) {
        this.cash -= cash;
        checkInterest();
    }

    @Override
    public double changeData(GregorianCalendar data) {
        double difference = 0;
        while (!this.dataDeposit.equals(data)) {
            if (!activation) {
                if (this.dataDeposit.get(GregorianCalendar.DAY_OF_MONTH) == 1) {
                    difference += this.additionalCash;
                    this.cash += this.additionalCash;
                    this.additionalCash = 0;
                }
                this.additionalCash += this.cash * this.interest;
                checkInterest();
                this.dataDeposit.add(GregorianCalendar.DAY_OF_MONTH, 1);
                checkData();
            }
        }
        return difference;
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
}
