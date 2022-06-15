package ru.itmo.banks;

import ru.itmo.banks.information.InterestInfo;
import ru.itmo.banks.information.TransactionInfo;
import ru.itmo.banks.observer.Observer;
import ru.itmo.banks.strategy.Card;
import ru.itmo.banks.strategy.CreditCard;
import ru.itmo.banks.strategy.DebitCard;
import ru.itmo.banks.strategy.DepositCard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CentralBank {
    private static CentralBank instance;
    private final List<Bank> banks;
    private final List<TransactionInfo> transactionInfo;
    private int bankId;
    private int clientId;
    private int cardId;
    private int transactionId;
    private GregorianCalendar data;

    private CentralBank() {
        this.banks = new ArrayList<>();
        this.transactionInfo = new ArrayList<>();
        this.bankId = 0;
        this.clientId = 0;
        this.cardId = 0;
        this.transactionId = 0;
        this.data = new GregorianCalendar(2002, Calendar.MAY, 30);
    }

    public static CentralBank getInstance() {
        if (instance == null) {
            instance = new CentralBank();
        }

        return instance;
    }

    public Bank addBank(double cash, InterestInfo info) {
        Bank newBank = new Bank(this.bankId, cash, info);
        this.bankId++;
        this.banks.add(newBank);
        System.out.println("Bank add");
        return newBank;
    }

    public void changeInterest(InterestInfo info, int bankId)  {
        Bank bank = findBank(bankId);
        if (bank == null) {
            throw new BanksException("there is no such bank");
        }
        bank.changeInterest(info);
        System.out.println(" Interest change ");
    }

    public void changeData(GregorianCalendar data)  {
        for (Bank bank : this.banks) {
            bank.changeData(data);
        }
        this.data = data;
        System.out.println(" Data change ");
    }

    public Client addClient(int bankId, String name, String fullName)  {
        Bank bank = findBank(bankId);
        if (bank == null) {
            throw new BanksException("there is no such bank");
        }
        Client client = new Client(bankId, this.clientId, name, fullName);
        this.clientId++;
        bank.addClient(client);
        System.out.println("Client add");
        return client;

    }

    public void addClientPassport(int passport, int clientId)  {
        Client client = findClient(clientId);
        if (client == null) {
            throw new BanksException("there is no such client");
        }
        client.addPassport(passport);
        System.out.println("Client Passport add");
    }

    public void addClientAddress(String address, int clientId)  {
        Client client = findClient(clientId);
        if (client == null) {
            throw new BanksException("there is no such client");
        }
        client.addAddress(address);
        System.out.println("Client Address add");
    }

    public Card addDebitCard(int clientId)  {
        Client client = findClient(clientId);
        if (client == null) {
            throw new BanksException("there is no such client");
        }

        Bank bank = findBank(client.getBankId());
        if (bank == null) {
            throw new BanksException("there is no such bank");
        }
        int year = this.data.get(GregorianCalendar.YEAR);
        int month= this.data.get(GregorianCalendar.MONTH);
        int day = this.data.get(GregorianCalendar.DAY_OF_MONTH);
        GregorianCalendar newData = new GregorianCalendar(year, month, day);
        Card card = new DebitCard(this.cardId, client.getBankId(), clientId, bank.getInterestInfo(), newData);
        this.cardId++;
        client.addCard(card);
        System.out.println("Debit card add");
        return card;
    }

    public Card addDepositCard(int clientId)  {
        Client client = findClient(clientId);
        if (client == null) {
            throw new BanksException("there is no such client");
        }

        Bank bank = findBank(client.getBankId());
        if (bank == null) {
            throw new BanksException("there is no such bank");
        }
        int year = this.data.get(GregorianCalendar.YEAR);
        int month= this.data.get(GregorianCalendar.MONTH);
        int day = this.data.get(GregorianCalendar.DAY_OF_MONTH);
        GregorianCalendar newData = new GregorianCalendar(year, month, day);
        Card card = new DepositCard(this.cardId, client.getBankId(), clientId, bank.getInterestInfo(), newData);
        this.cardId++;
        client.addCard(card);
        System.out.println("Deposit card add");
        return card;
    }

    public Card addCreditCard(int clientId) {
        Client client = findClient(clientId);
        if (client == null) {
            throw new BanksException("there is no such client");
        }

        Bank bank = findBank(client.getBankId());
        if (bank == null) {
            throw new BanksException("there is no such bank");
        }
        int year = this.data.get(GregorianCalendar.YEAR);
        int month= this.data.get(GregorianCalendar.MONTH);
        int day = this.data.get(GregorianCalendar.DAY_OF_MONTH);
        GregorianCalendar newData = new GregorianCalendar(year, month, day);
        Card card = new CreditCard(this.cardId, client.getBankId(), clientId, bank.getInterestInfo(), newData);
        this.cardId++;
        client.addCard(card);
        System.out.println("Credit card add");
        return card;
    }

    public void withdrawal(int cardId, double cash) {
        Card card = findCard(cardId);
        if (card == null) {
            throw new BanksException("there is no such card");
        }

        Client client = findClient(card.getClientId());
        if (client == null) {
            throw new BanksException("there is no such client");
        }

        if (!client.isActivation()) {
            throw new BanksException("This Client cannot do operations ");
        }
        card.withdrawal(cash);
        System.out.println(" Operation completed ");
    }

    public void replenishment(int cardId, double cash) {
        Card card = findCard(cardId);
        if (card == null) {
            throw new BanksException("there is no such card");
        }

        Client client = findClient(card.getClientId());
        if (client == null) {
            throw new BanksException("there is no such client");
        }

        if (!client.isActivation()) {
            throw new BanksException("This Client cannot do operations ");
        }

        card.replenishment(cash);
        System.out.println(" Operation completed ");
    }

    public int moneyTransfer(int cardIdOne, int cardIdTwo, double cash) throws BanksException {
        Card cardOne = findCard(cardIdOne);
        if (cardOne == null) {
            throw new BanksException("there is no such card");
        }

        Card cardTwo = findCard(cardIdTwo);
        if (cardTwo == null) {
            throw new BanksException("there is no such card");
        }

        Client clientOne = findClient(cardOne.getClientId());
        Client clientTwo = findClient(cardTwo.getClientId());

        if (!clientOne.isActivation() || !clientTwo.isActivation()) {
            throw new BanksException("This Client can't do operations ");
        }

        cardOne.withdrawal(cash);
        cardTwo.replenishment(cash);
        var transactionInfo = new TransactionInfo(cardIdOne, cardIdTwo, cash, this.transactionId, this.data);
        this.transactionId++;
        this.transactionInfo.add(transactionInfo);
        System.out.println(" Operation completed ");
        return transactionInfo.getTransactionId();
    }

    public void cancelMoneyTransfer(int transactionId) {
        TransactionInfo transaction = findTransaction(transactionId);
        if (transaction == null) {
            throw new BanksException("there is no such Transaction");
        }

        Card cardOne = findCard(transaction.getCardOneId());
        if (cardOne == null) {
            throw new BanksException("there is no such card");
        }

        Card cardTwo = findCard(transaction.getCardTwoId());
        if (cardTwo == null) {
            throw new BanksException("there is no such card");
        }

        double cash = transaction.getCash();
        cardTwo.withdrawal(cash);
        cardOne.replenishment(cash);
        this.transactionInfo.remove(transaction);
        System.out.println(" Operation completed ");
    }

    public void attachObserver(Observer observer, int bankId) {
        Bank bank = findBank(bankId);
        if (bank == null) {
            throw new BanksException("there is no such bank");
        }
        bank.attach(observer);
        System.out.println(" Observer attach ");
    }

    public List<Bank> getBanks() {
        return this.banks;
    }

    private Bank findBank(int bankId) {
        for (Bank bank : this.banks) {
            if (bank.getBankId() == bankId) {
                return bank;
            }
        }
        return null;
    }

    private Client findClient(int clientId) {
        for (Bank bank : this.banks) {
            for (Client client : bank.getClients()) {
                if (client.getClientId() == clientId) {
                    return client;
                }
            }
        }
        return null;
    }

    private Card findCard(int cardId) {
        for (Bank bank : this.banks) {
            for (Client client : bank.getClients()) {
                for (Card card : client.getCards()) {
                    if (card.getCardId() == cardId) {
                        return card;
                    }
                }
            }
        }
        return null;
    }

    private TransactionInfo findTransaction(int transactionId) {
        for (TransactionInfo transaction : this.transactionInfo) {
            if (transaction.getTransactionId() == transactionId) {
                return transaction;
            }
        }
        return null;
    }
}
