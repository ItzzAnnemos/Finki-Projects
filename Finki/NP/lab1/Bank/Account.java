package mk.ukim.finki.NP.lab1.Bank;

import java.util.Random;
public class Account {
    private String name;
    private long idNumber;
    private String balance;

    public Account(String name, String balance) {
        this.name = name;
        this.balance = balance;

        Random random = new Random();

        this.idNumber = random.nextLong();
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return idNumber;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String toString() {
        String ret = "";
        ret += "Name: " + name + '\n';
        ret += "Balance: " + balance + '\n';
        return ret;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        Account tmp = (Account) o;

        if (idNumber == tmp.idNumber && name.equals(tmp.name) && balance.equals(tmp.balance)) {
            return true;
        } else
            return false;
    }
}
