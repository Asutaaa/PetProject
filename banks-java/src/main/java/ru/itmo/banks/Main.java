package ru.itmo.banks;

import java.util.Scanner;

public class Main {
    public static void main(String[] Args) throws BanksException {
        ConsoleForUser consoleForUser = new ConsoleForUser();
        System.out.println(" Input start: ");
        String n = "Start";
        Scanner console = new Scanner(System.in);
        while (!n.equalsIgnoreCase("Stop")){
            n = console.next();
            consoleForUser.input(n);
        }
    }
}
