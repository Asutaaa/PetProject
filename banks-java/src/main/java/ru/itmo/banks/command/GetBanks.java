package ru.itmo.banks.command;

import ru.itmo.banks.Bank;
import ru.itmo.banks.CentralBank;

public class GetBanks {
    private CentralBank centralBank = CentralBank.getInstance();

    public GetBanks() {
        for (Bank bank : centralBank.getBanks()) {
            System.out.print(bank.getBankId() + "  ");
        }
    }
}
