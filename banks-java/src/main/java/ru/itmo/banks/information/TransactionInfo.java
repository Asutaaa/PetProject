package ru.itmo.banks.information;

import java.util.Calendar;

public class TransactionInfo {
    private final int cardOneId;
    private final int cardTwoId;
    private final double cash;
    private final int transactionId;
    private final Calendar data;

    public TransactionInfo(int cardOneId, int cardTwoId, double cash, int transactionId, Calendar data) {
        this.cardOneId = cardOneId;
        this.cardTwoId = cardTwoId;
        this.cash = cash;
        this.transactionId = transactionId;
        this.data = data;
    }

    public int getCardOneId() {
        return cardOneId;
    }

    public int getCardTwoId() {
        return cardTwoId;
    }

    public double getCash() {
        return cash;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public Calendar getData() {
        return data;
    }
}
