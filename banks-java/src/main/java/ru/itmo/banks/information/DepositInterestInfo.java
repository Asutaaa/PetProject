package ru.itmo.banks.information;

import ru.itmo.banks.BanksException;

public class DepositInterestInfo {
    private final double maxCash;
    private final double minCash;
    private final double interest;

    public DepositInterestInfo(double maxCash, double minCash, double interest) {
        check(maxCash, minCash, interest);
        this.maxCash = maxCash;
        this.minCash = minCash;
        this.interest = interest;
    }

    public double getMaxCash() {
        return maxCash;
    }

    public double getMinCash() {
        return minCash;
    }

    public double getInterest() {
        return interest;
    }

    private void check(double maxCash, double minCash, double interest) {
        if (maxCash < minCash || interest > 1.0 || interest < 0.0 || maxCash < 0 || minCash < 0) {
            throw new BanksException("incorrect  interest");
        }
    }
}
