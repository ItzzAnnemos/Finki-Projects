package mk.ukim.finki.NP.lab1.RomanNumber;

public class RomanConverter {
    public static String toRoman(int n) {
        String ret = "";
        int tmp =  n / 1000;
        for (int i = 0;i < tmp;i++) {
            ret += "M";
        }
        n -= (tmp * 1000);

        tmp = n / 100;
        switch (tmp) {
            case (9):
                ret += "CM";
                break;
            case (8):
                ret += "DCCC";
                break;
            case (7):
                ret += "DCC";
                break;
            case(6):
                ret += "DC";
                break;
            case(5):
                ret += "D";
                break;
            case (4):
                ret += "CD";
                break;
            case (3):
                ret += "CCC";
                break;
            case(2):
                ret += "CC";
                break;
            case (1):
                ret += "C";
                break;
        }

        n -= (tmp * 100);

        tmp = n / 10;

        switch (tmp) {
            case (9):
                ret += "XC";
                break;
            case (8):
                ret += "LXXX";
                break;
            case (7):
                ret += "LXX";
                break;
            case(6):
                ret += "LX";
                break;
            case(5):
                ret += "L";
                break;
            case (4):
                ret += "XL";
                break;
            case (3):
                ret += "XXX";
                break;
            case(2):
                ret += "XX";
                break;
            case (1):
                ret += "X";
                break;
        }

        n -= (tmp * 10);

        switch (n) {
            case (9):
                ret += "IX";
                break;
            case (8):
                ret += "VIII";
                break;
            case (7):
                ret += "VII";
                break;
            case(6):
                ret += "VI";
                break;
            case(5):
                ret += "V";
                break;
            case (4):
                ret += "IV";
                break;
            case (3):
                ret += "III";
                break;
            case(2):
                ret += "II";
                break;
            case (1):
                ret += "I";
                break;
        }

        return ret;
    }
}
