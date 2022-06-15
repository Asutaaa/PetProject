package ru.itmo.banks.command;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.CentralBank;

import java.util.Scanner;

public class Withdrawal {
    private CentralBank centralBank = CentralBank.getInstance();

    public Withdrawal() {
        System.out.println(" Input index card: ");
        Scanner console = new Scanner(System.in);
        int index = console.nextInt();
        System.out.println(" Input cash: ");
        double cash = console.nextDouble();
        centralBank.withdrawal(index, cash);
    }
}
