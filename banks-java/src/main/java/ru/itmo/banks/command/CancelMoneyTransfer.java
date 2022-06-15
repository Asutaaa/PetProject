package ru.itmo.banks.command;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.CentralBank;

import java.util.Scanner;

public class CancelMoneyTransfer {
    private CentralBank centralBank = CentralBank.getInstance();

    public CancelMoneyTransfer() {
        System.out.println(" Input index transaction : ");
        Scanner console = new Scanner(System.in);
        int index = console.nextInt();
        centralBank.cancelMoneyTransfer(index);
    }
}
