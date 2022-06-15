package ru.itmo.banks;

import ru.itmo.banks.command.*;

import java.util.Scanner;

public class ConsoleForUser {
    public ConsoleForUser() {
    }

    public void input(String answer) throws BanksException {
        System.out.println("select: view banks-1, add bank - 2, Change Interest Bank - 3, Add client - 4, Add Client Passport - 5," +
                " Add Client Address -6, Add Debit Card - 7, Add Deposit Card - 8, Add Credit Card - 9, Withdrawal - 10" +
                "Replenishment - 11, MoneyTransfer - 12, CancelMoneyTransfer - 13");
        Scanner console = new Scanner(System.in);
        answer = console.next();
        switch (answer) {
            case "1":
                var getBanks = new GetBanks();
                break;
            case "2":
                var addBank = new AddBank();
                break;
            case "3":
                var changeInterest = new ChangeInterest();
                break;
            case "4":
                var addClient = new AddClient();
                break;
            case "5":
                var addPassport = new AddClientPassport();
                break;
            case "6":
                var addAddress = new AddClientAddress();
                break;
            case "7":
                var addDebitCard = new AddDebitCard();
                break;
            case "8":
                var addDepositCard = new AddDepositCard();
                break;
            case "9":
                var addCreditCard = new AddCreditCard();
                break;
            case "10":
                var withdrawal = new Withdrawal();
                break;
            case "11":
                var replenishment = new Replenishment();
                break;
            case "12":
                var moneyTransfer = new MoneyTransfer();
                break;
            case "13":
                var cancelMoneyTransfer = new CancelMoneyTransfer();
                break;
        }
    }
}
