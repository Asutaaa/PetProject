package ru.itmo.banks.command;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.CentralBank;
import ru.itmo.banks.information.InterestInfo;

import java.util.Scanner;

public class AddBank {
    private final CentralBank centralBank = CentralBank.getInstance();

    public AddBank() {
        AddInterestInfo addInterestInfo = new AddInterestInfo();
        InterestInfo interestInfo = addInterestInfo.getInterestInfo();
        System.out.println(" Input Cash Bank: ");
        Scanner console = new Scanner(System.in);
        double cash = console.nextDouble();
        centralBank.addBank(cash, interestInfo);
    }
}
