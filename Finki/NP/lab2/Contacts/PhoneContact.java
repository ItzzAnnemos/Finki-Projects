package mk.ukim.finki.NP.lab2.Contacts;

enum Operator { VIP, ONE, TMOBILE }

public class PhoneContact extends Contact{
    String phone;
    Operator op;
    public PhoneContact(String date, String phone) {
        super(date);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public Operator getOperator() {
        String tmp = phone.substring(0,3);
        if (tmp.equals("070") || tmp.equals("071") || tmp.equals("072")) {
            return Operator.TMOBILE;
        } else if (tmp.equals("075") || tmp.equals("076")) {
            return Operator.ONE;
        } else if (tmp.equals("077") || tmp.equals("078")){
            return Operator.VIP;
        }
        return null;
    }

    @Override
    public String getType() {
        return "Phone";
    }
}
