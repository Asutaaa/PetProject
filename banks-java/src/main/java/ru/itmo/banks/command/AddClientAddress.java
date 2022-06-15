package ru.itmo.banks.command;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.CentralBank;

import java.util.Scanner;

public class AddClientAddress {
    private final CentralBank centralBank = CentralBank.getInstance();

    public AddClientAddress(){
        System.out.println(" Input index client: ");
        Scanner console = new Scanner(System.in);
        int index = console.nextInt();
        System.out.println(" Input address: ");
        String address = console.next();
        centralBank.addClientAddress(address, index);
    }
}
