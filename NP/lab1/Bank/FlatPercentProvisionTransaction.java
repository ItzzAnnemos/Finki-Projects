package mk.ukim.finki.NP.lab1.Bank;

public class FlatPercentProvisionTransaction extends Transaction{
    private int centPerDolar;

    public FlatPercentProvisionTransaction(long fromId, long toId, String amount, int centsPerDolar) {
        super(fromId, toId, "FlatPercent", amount);
        this.centPerDolar = centsPerDolar;
    }

    private double parseStringToDouble(String tmp) {
        return Double.parseDouble(tmp.substring(0, tmp.length() - 1));
    }

    @Override
    public double getProvision() {
        return (centPerDolar / 100.0) * (int) parseStringToDouble(super.getAmount());
    }

    public int getPercent() {
        return centPerDolar;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = super.hashCode();
        hash = hash * prime + centPerDolar;

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (!(o instanceof FlatPercentProvisionTransaction))
            return false;

        return super.equals(o);
    }
}
