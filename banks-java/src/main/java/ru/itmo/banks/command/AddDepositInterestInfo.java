package ru.itmo.banks.command;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.information.DepositInterestInfo;

import java.util.Scanner;

public class AddDepositInterestInfo {
    public AddDepositInterestInfo() {
    }

    public DepositInterestInfo depositInterest() {
        System.out.println(" Input maxCash: ");
        Scanner console = new Scanner(System.in);
        double maxCash = console.nextDouble();
        System.out.println(" Input minCash: ");
        double minCash = console.nextDouble();
        System.out.println(" Input interest: ");
        double interest = console.nextDouble();
        return new DepositInterestInfo(maxCash, minCash, interest);
    }
}
