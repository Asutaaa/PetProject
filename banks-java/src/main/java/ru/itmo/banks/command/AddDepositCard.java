package ru.itmo.banks.command;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.CentralBank;

import java.util.Scanner;

public class AddDepositCard {
    private final CentralBank centralBank = CentralBank.getInstance();

    public AddDepositCard() {
        System.out.println(" Input index client: ");
        Scanner console = new Scanner(System.in);
        int index = console.nextInt();
        centralBank.addDepositCard(index);
    }
}
