package mk.ukim.finki.NP.lab2.Contacts;

public abstract class Contact {
    private String date;

    public Contact(String date) {
        this.date = date;
    }

    private int getYear() {
        return Integer.parseInt(date.substring(0, 4));
    }

    private int getMonth() {
        return Integer.parseInt(date.substring(5, 7));
    }

    private int getDay() {
        return Integer.parseInt(date.substring(8, 10));
    }

    public boolean isNewerThan(Contact c) {
        if (this.getYear() > c.getYear()) {
            return true;
        } else if (this.getYear() < c.getYear()) {
            return false;
        }

        if (this.getMonth() > c.getMonth()) {
            return true;
        } else if (this.getMonth() < c.getMonth()) {
            return false;
        }

        return this.getDay() > c.getDay();
    }

    public abstract String getType();
}
