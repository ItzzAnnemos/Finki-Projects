package mk.ukim.finki.NP.lab3.Phonebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Contact {
    private String name;
    private List<String> phoneNumbers;

    public Contact(String name, String... phoneNumbers) throws InvalidNameException, InvalidNumberException, MaximumSizeExceddedException {

        checkName(name);
        this.name = name;

        checkPhoneNumber(phoneNumbers);
        this.phoneNumbers = new ArrayList<>();
        this.phoneNumbers.addAll(Arrays.asList(phoneNumbers));
    }

    private void checkName(String name) throws InvalidNameException {
        if (name.length() < 5 || name.length() > 10)
            throw new InvalidNameException(name);

        char[] tmp = name.toCharArray();
        for (char c : tmp) {
            if (!Character.isLetterOrDigit(c))
                throw new InvalidNameException(name);
        }
    }

    private void checkPhoneNumber(String[] phoneNumber) throws InvalidNumberException, MaximumSizeExceddedException {
        if (phoneNumber.length > 5)
            throw new MaximumSizeExceddedException();
        for (String number : phoneNumber) {
            if (number.length() != 9)
                throw new InvalidNumberException();

            String tmp = number.substring(0, 3);
            if (!(tmp.equals("070") || tmp.equals("071") || tmp.equals("072")
                    || tmp.equals("075") || tmp.equals("076") || tmp.equals("077")
                    || tmp.equals("078")))
                throw new InvalidNumberException();

            for (int i = 0; i < number.length(); i++) {
                if (!Character.isDigit(number.charAt(i)))
                    throw new InvalidNumberException();
            }
        }
    }

    public String getName() {
        return name;
    }

    public String[] getNumbers() {
        List<String> tmp = new ArrayList<>(phoneNumbers);

        Collections.sort(tmp);

        return tmp.toArray(String[]::new);
    }

    public void addNumber(String phonenumber) throws InvalidNumberException {
        if (phoneNumbers.size() == 5) {
            return;
        }

        if (phonenumber.length() != 9)
            throw new InvalidNumberException();

        String tmp = phonenumber.substring(0, 3);
        if (!(tmp.equals("070") || tmp.equals("071") || tmp.equals("072")
                || tmp.equals("075") || tmp.equals("076") || tmp.equals("077")
                || tmp.equals("078")))
            throw new InvalidNumberException();

        for (int i = 0; i < phonenumber.length(); i++) {
            if (!Character.isDigit(phonenumber.charAt(i)))
                throw new InvalidNumberException();
        }

        this.phoneNumbers.add(phonenumber);
    }

    @Override
    public String toString() {
        String ret = "";
        ret += name + "\n";
        ret += phoneNumbers.size() + "\n";
        for (String num : getNumbers()) {
            ret += num + "\n";
        }

        return ret;
    }

    public static Contact valueOf(String s) throws InvalidFormatException, InvalidNameException, InvalidNumberException, MaximumSizeExceddedException {
        String[] parts = s.split("\\s+");
        if (parts[0].length() < 5 || parts[0].length() > 10)
            throw new InvalidNameException(parts[0]);

        char[] tmp = parts[0].toCharArray();
        for (char c : tmp) {
            if (!Character.isLetterOrDigit(c))
                throw new InvalidNameException(parts[0]);
        }

        if (parts.length > 6) {
            throw new InvalidFormatException();
        }

        String[] tmpNumbers = new String[5];
        int n = 0;

        for (int i = 1; i < parts.length; i++) {
            if (parts[i].length() == 9) {
                if (parts[i].startsWith("070") || parts[i].startsWith("071") || parts[i].startsWith("072")
                        || parts[i].startsWith("075") || parts[i].startsWith("076") || parts[i].startsWith("077")
                        || parts[i].startsWith("078")) {
                    tmpNumbers[n] = parts[i];
                    n++;
                } else {
                    throw new InvalidFormatException();
                }
            } else {
                throw new InvalidFormatException();
            }
        }

        return new Contact(parts[0], tmpNumbers);
    }
}
