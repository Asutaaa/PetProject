package ru.itmo.banks.command;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.CentralBank;

import java.util.Scanner;

public class AddClientPassport {
    private final CentralBank centralBank = CentralBank.getInstance();

    public AddClientPassport()  {
        System.out.println(" Input index client: ");
        Scanner console = new Scanner(System.in);
        int index = console.nextInt();
        System.out.println(" Input passport: ");
        int passport = console.nextInt();
        centralBank.addClientPassport(passport, index);
    }
}
