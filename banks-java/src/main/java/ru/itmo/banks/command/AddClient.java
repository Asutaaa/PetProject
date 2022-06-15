package ru.itmo.banks.command;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.CentralBank;

import java.util.Scanner;

public class AddClient {
    private final CentralBank centralBank = CentralBank.getInstance();

    public AddClient()  {
        System.out.println(" Input index bank: ");
        Scanner console = new Scanner(System.in);
        int index = console.nextInt();
        System.out.println(" Input name client: ");
        String name = console.next();
        System.out.println(" Input fullName client: ");
        String fullName = console.next();
        centralBank.addClient(index, name, fullName);
    }
}
