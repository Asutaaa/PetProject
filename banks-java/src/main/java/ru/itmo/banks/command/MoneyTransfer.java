package ru.itmo.banks.command;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.CentralBank;

import java.util.Scanner;

public class MoneyTransfer {
    private CentralBank centralBank = CentralBank.getInstance();

    public MoneyTransfer() {
        System.out.println(" Input index card one : ");
        Scanner console = new Scanner(System.in);
        int indexOne = console.nextInt();
        System.out.println(" Input index card two : ");
        int indexTwo = console.nextInt();
        System.out.println(" Input cash: ");
        double cash = console.nextDouble();
        centralBank.moneyTransfer(indexOne, indexTwo, cash);
    }
}
