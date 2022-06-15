package ru.itmo.banks.information;

import ru.itmo.banks.BanksException;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class InterestInfo {
    private final List<DepositInterestInfo> depositInterest;
    private final double debitInterest;
    private final double creditInterest;
    private final double maxCashCreditCard;
    private final GregorianCalendar fixDataDepositCard;

    public InterestInfo(double debitInterest, double creditInterest, List<DepositInterestInfo> depositInterest, double maxCashCreditCard, GregorianCalendar fixDataDepositCard) {
        check(debitInterest);
        check(creditInterest);
        this.depositInterest = depositInterest;
        this.debitInterest = debitInterest;
        this.creditInterest = creditInterest;
        this.maxCashCreditCard = maxCashCreditCard;
        this.fixDataDepositCard = fixDataDepositCard;
    }

    private void check(double interest) {
        if (interest > 1.0 || interest < 0.0) {
            throw new BanksException("incorrect  interest");
        }
    }

    public List<DepositInterestInfo> getDepositInterest() {
        return depositInterest;
    }

    public double getDebitInterest() {
        return debitInterest;
    }

    public double getCreditInterest() {
        return creditInterest;
    }

    public double getMaxCashCreditCard() {
        return maxCashCreditCard;
    }

    public GregorianCalendar getFixDataDepositCard() {
        return fixDataDepositCard;
    }
}
