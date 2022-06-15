package ru.itmo.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import ru.itmo.banks.Bank;
import ru.itmo.banks.BanksException;
import ru.itmo.banks.CentralBank;
import ru.itmo.banks.Client;
import ru.itmo.banks.information.DepositInterestInfo;
import ru.itmo.banks.information.InterestInfo;
import ru.itmo.banks.observer.ObserverImpl;
import ru.itmo.banks.strategy.Card;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Tests {
    private CentralBank centralBank;
    private Bank bank;
    private Client client;

    @BeforeEach
    public void setUp() throws BanksException {
        centralBank = CentralBank.getInstance();
        ArrayList<DepositInterestInfo> depositInterest = new ArrayList<DepositInterestInfo>() {
        };
        depositInterest.add(new DepositInterestInfo(1000000, 0, 0.0365));
        depositInterest.add(new DepositInterestInfo(Double.MAX_VALUE, 1000000, 0.01825));
        InterestInfo interest = new InterestInfo(0.0365, 0.0365, depositInterest, 1000000, new GregorianCalendar(2024, 12, 21));
        bank = centralBank.addBank(1000000, interest);
        client = centralBank.addClient(bank.getBankId(), "Anastasia", "Bezzubtceva");
        centralBank.addClientAddress("Nevsky Prospect", client.getClientId());
        centralBank.addClientPassport(1245462, client.getClientId());

    }

    @Test
    public void checkingForTransactionsOnDoubtfulAccountsClientException() throws BanksException {
        Client clientOne = centralBank.addClient(bank.getBankId(), "Nik", "Small");
        Client clientTwo = centralBank.addClient(bank.getBankId(), "Kate", "Small");
        centralBank.addClientAddress("maly prospekt", clientOne.getClientId());
        centralBank.addClientPassport(1243235, clientTwo.getClientId());

        Card cardOne = centralBank.addDebitCard(clientOne.getClientId());
        Card cardTwo = centralBank.addDebitCard(clientTwo.getClientId());

        Assertions.assertThrows(BanksException.class, () -> centralBank.replenishment(cardOne.getCardId(), 1000));
        Assertions.assertThrows(BanksException.class, () -> centralBank.replenishment(cardTwo.getCardId(), 1000));
        Assertions.assertThrows(BanksException.class, () -> centralBank.withdrawal(cardOne.getCardId(), 1000));
        Assertions.assertThrows(BanksException.class, () -> centralBank.withdrawal(cardTwo.getCardId(), 1000));

        centralBank.addClientPassport(4526481, clientOne.getClientId());
        centralBank.replenishment(cardOne.getCardId(), 1000);

        Assertions.assertThrows(BanksException.class, () -> centralBank.moneyTransfer(cardOne.getCardId(), cardTwo.getCardId(), 1000));
    }

    @Test
    public void changeInterest_ChangeInterestInBank() throws BanksException {
        ArrayList<DepositInterestInfo> depositInterestNew = new ArrayList<DepositInterestInfo>();
        depositInterestNew.add(new DepositInterestInfo(1000, 0, 0.0365));
        depositInterestNew.add(new DepositInterestInfo(Double.MAX_VALUE, 1000000, 0.01825));
        InterestInfo interestNew = new InterestInfo(0.0465, 0.0465, depositInterestNew, 10000000, new GregorianCalendar(2024, 12, 21));
        ObserverImpl observer = new ObserverImpl("Katya");
        centralBank.attachObserver(observer, bank.getBankId());
        centralBank.changeInterest(interestNew, bank.getBankId());
        Assertions.assertEquals(bank.getInterestInfo(), interestNew);
    }

    @Test
    public void changeData_TransactionException() throws BanksException {
        centralBank.changeData(new GregorianCalendar(2024, 10, 20));


        centralBank.addClientAddress("Nevsky Prospect", client.getClientId());
        centralBank.addClientPassport(1245462, client.getClientId());
        Card debitCard = centralBank.addDebitCard(client.getClientId());
        Card creditCard = centralBank.addCreditCard(client.getClientId());
        Card depositCard = centralBank.addDepositCard(client.getClientId());

        centralBank.replenishment(debitCard.getCardId(), 1000);//1000-cash
        centralBank.withdrawal(creditCard.getCardId(), 1000);//1000-cash
        centralBank.replenishment(depositCard.getCardId(), 1000);//1000-cash

        centralBank.changeData(new GregorianCalendar(2024, 11, 20));
        Assertions.assertEquals(debitCard.getCash(), 1001.0);
        Assertions.assertEquals(creditCard.getCash(), -1001.0);
        Assertions.assertEquals(depositCard.getCash(), 1001.1);

        Assertions.assertThrows(BanksException.class, () -> centralBank.withdrawal(debitCard.getCardId(), 1000000));
        Assertions.assertThrows(BanksException.class, () -> centralBank.withdrawal(creditCard.getCardId(), 100000000));
        Assertions.assertThrows(BanksException.class, () -> centralBank.withdrawal(depositCard.getCardId(), 100));
    }
}