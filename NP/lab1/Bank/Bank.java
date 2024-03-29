package mk.ukim.finki.NP.lab1.Bank;

import java.util.Arrays;

public class Bank {
    private String name;
    private Account[] accounts;
    private double sumTransfers;
    private double sumProvision;

    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.accounts = new Account[accounts.length];
        this.accounts = accounts;
        sumTransfers = 0;
        sumProvision = 0;
    }

    private double parseStringToDouble(String tmp) {
        return Double.parseDouble(tmp.substring(0, tmp.length() - 1));
    }

    public boolean makeTransaction(Transaction t) {
        int flag1 = 0, flag2 = 0;
        int indexFrom = 0, indexTo = 0;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getId() == t.fromId) {
                flag1 = 1;
                indexFrom = i;
            }
            if (accounts[i].getId() == t.toId) {
                flag2 = 1;
                indexTo = i;
            }
        }
        if (flag1 == 1 && flag2 == 1) {
            double balanceFrom = parseStringToDouble(accounts[indexFrom].getBalance());
            double balanceTo = parseStringToDouble(accounts[indexTo].getBalance());
            double amount = parseStringToDouble(t.getAmount());
            double provision = t.getProvision();

            if (balanceFrom >= amount) {
                sumTransfers += amount;
                sumProvision += provision;
                balanceFrom -= (amount + provision);
                balanceTo += amount;

                accounts[indexFrom].setBalance(String.format("%.2f", balanceFrom) + "$");
                accounts[indexTo].setBalance(String.format("%.2f", balanceTo) + "$");
                return true;
            }
        }

        return false;
    }

    public Account [] getAccounts() {
        return accounts;
    }

    public String totalTransfers() {
        return String.format("%.2f", sumTransfers) + "$";
    }

    public String totalProvision() {
        return String.format("%.2f", sumProvision) + "$";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(name);
        sb.append("\n\n");
        for (Account account : accounts) {
            sb.append(account);
        }

        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + name.hashCode();
        hash = hash * prime + (int) sumTransfers;
        hash = hash * prime + (int) sumProvision;
        hash = hash * prime + Arrays.hashCode(accounts);

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (this.getClass() != o.getClass())
            return false;

        Bank tmp = (Bank) o;
        if (this.name.equals(tmp.name) && Arrays.equals(this.accounts, tmp.accounts) && this.sumTransfers == tmp.sumTransfers && this.sumProvision == sumProvision && this.hashCode() == tmp.hashCode()) {
            return true;
        }

        return false;
    }
}
