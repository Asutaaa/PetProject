package ru.itmo.banks.command;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.CentralBank;
import ru.itmo.banks.information.InterestInfo;

import java.util.Scanner;

public class ChangeInterest {
    private final CentralBank centralBank = CentralBank.getInstance();

    public ChangeInterest() {
        AddInterestInfo addInterestInfo = new AddInterestInfo();
        InterestInfo interestInfo = addInterestInfo.getInterestInfo();
        System.out.println(" Input index bank: ");
        Scanner console = new Scanner(System.in);
        int index = console.nextInt();
        centralBank.changeInterest(interestInfo, index);
    }
}
