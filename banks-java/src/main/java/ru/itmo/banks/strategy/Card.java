package ru.itmo.banks.strategy;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.information.InterestInfo;

import java.util.GregorianCalendar;

public interface Card {
    void withdrawal(double cash) throws BanksException;

    void replenishment(double cash);

    void changeInterest(InterestInfo interest);

    int getNameCard();

    void cancellationOfTheTransaction(double cash);

    double changeData(GregorianCalendar data) throws BanksException;

    int getCardId();

    int getClientId();

    int getBankId();

    double getCash();
}
