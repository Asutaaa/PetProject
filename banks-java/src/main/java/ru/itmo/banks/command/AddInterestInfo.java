package ru.itmo.banks.command;

import ru.itmo.banks.BanksException;
import ru.itmo.banks.information.DepositInterestInfo;
import ru.itmo.banks.information.InterestInfo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class AddInterestInfo {
    public AddInterestInfo() {
    }

    public InterestInfo getInterestInfo() {
        System.out.println(" Input debit interest: ");
        Scanner console = new Scanner(System.in);
        double debitInterest = console.nextDouble();

        System.out.println(" Input credit interest: ");
        double creditInterest = console.nextDouble();

        System.out.println(" Input max cash credit card: ");
        double maxCashCreditCard = console.nextDouble();

        System.out.println(" Input fix data deposit card year: ");
        int year = console.nextInt();

        System.out.println(" Input fix data deposit card month: ");
        int month = console.nextInt();

        System.out.println(" Input fix data deposit card day: ");
        int day = console.nextInt();

        System.out.println(" Input deposit interests: ");
        ArrayList<DepositInterestInfo> depositInterest = getDepositInterest();
        InterestInfo interestInfo = new InterestInfo(debitInterest, creditInterest, depositInterest, maxCashCreditCard, new GregorianCalendar(year, month, day));
        return interestInfo;
    }

    private ArrayList<DepositInterestInfo> getDepositInterest() throws BanksException {
        AddDepositInterestInfo addDepositInterestInfo = new AddDepositInterestInfo();
        ArrayList<DepositInterestInfo> list = new ArrayList<DepositInterestInfo>();
        DepositInterestInfo depositInterestInfo;
        depositInterestInfo = addDepositInterestInfo.depositInterest();
        list.add(depositInterestInfo);
        System.out.println(" Add more deposit terms? ");
        System.out.println(" YES/NO ");
        Scanner console = new Scanner(System.in);
        String answer = console.next();
        while (answer.equalsIgnoreCase("YES")) {
            depositInterestInfo = addDepositInterestInfo.depositInterest();
            list.add(depositInterestInfo);
            System.out.println(" Add more deposit terms? ");
            System.out.println(" YES/NO ");
            answer = console.next();
        }
        return list;
    }
}
