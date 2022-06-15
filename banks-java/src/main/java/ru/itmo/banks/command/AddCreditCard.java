package ru.itmo.banks.command;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.CentralBank;

import java.util.Scanner;

public class AddCreditCard {
    private final CentralBank centralBank = CentralBank.getInstance();

    public AddCreditCard()  {
        System.out.println(" Input index client: ");
        Scanner console = new Scanner(System.in);
        int index = console.nextInt();
        centralBank.addCreditCard(index);
    }
}
