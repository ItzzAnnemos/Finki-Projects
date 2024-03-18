package mk.ukim.finki.NP.lab1.Bank;

public abstract class Transaction {
    protected long fromId;
    protected long toId;
    protected String description;
    protected String amount;

    public Transaction(long fromId, long toId, String description, String amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.description = description;
        this.amount = amount;
    }

    public long getFromId() {
        return fromId;
    }

    public long getToId() {
        return toId;
    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        return amount;
    }

    public abstract double getProvision();

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + (int) fromId;
        hash = prime * hash + (int) toId;
        hash = prime * hash + description.hashCode();
        hash = prime * hash + amount.hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FlatPercentProvisionTransaction) {
            FlatPercentProvisionTransaction tmp = (FlatPercentProvisionTransaction) o;
            if (this.fromId == tmp.getFromId() && this.toId == tmp.getToId() && this.description.equals(tmp.getDescription()) && this.amount.equals(tmp.getAmount()) && this.hashCode() == tmp.hashCode()) {
                return true;
            } else
                return false;
        } else if (o instanceof FlatAmountProvisionTransaction) {
            FlatAmountProvisionTransaction tmp = (FlatAmountProvisionTransaction) o;
            if (this.fromId == tmp.getFromId() && this.toId == tmp.getToId() && this.description.equals(tmp.getDescription()) && this.amount.equals(tmp.getAmount()) && this.hashCode() == tmp.hashCode()) {
                return true;
            } else
                return false;
        } else
            return false;
    }
}
