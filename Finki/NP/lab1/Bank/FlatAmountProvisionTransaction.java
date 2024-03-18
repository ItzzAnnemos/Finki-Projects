package mk.ukim.finki.NP.lab1.Bank;

public class FlatAmountProvisionTransaction extends Transaction {
    private String flatProvision;

    public FlatAmountProvisionTransaction(long fromId, long toId, String amount, String flatProvision) {
        super(fromId, toId, "FlatAmount", amount);
        this.flatProvision = flatProvision;
    }

    private double parseStringToDouble(String tmp) {
        return Double.parseDouble(tmp.substring(0, tmp.length() - 1));
    }

    @Override
    public double getProvision() {
        return parseStringToDouble(flatProvision);
    }

    public String getFlatAmount() {
        return flatProvision;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = super.hashCode();
        hash = hash * prime + flatProvision.hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (!(o instanceof FlatAmountProvisionTransaction))
            return true;

        return super.equals(o);
    }
}
