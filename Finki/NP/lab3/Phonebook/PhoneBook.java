package mk.ukim.finki.NP.lab3.Phonebook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PhoneBook {
    private List<Contact> contacts;

    public PhoneBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) throws MaximumSizeExceddedException, InvalidNameException {
        if (contacts.isEmpty()) {
            contacts.add(contact);
        } else {
            if (contacts.size() > 250) {
                throw new MaximumSizeExceddedException();
            }

            boolean flag = true;
            for (Contact value : contacts) {
                if (value.getName().equals(contact.getName())) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                contacts.add(contact);
            } else {
                throw new InvalidNameException(contact.getName());
            }
        }
    }

    public Contact getContactForName(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }

        return null;
    }

    public int numberOfContacts() {
        return contacts.size();
    }

    public Contact[] getContacts() {
        return contacts.stream()
                .sorted(Comparator.comparing(Contact::getName))
                .toArray(Contact[]::new);
    }

    public boolean removeContact(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(name)) {
                contacts.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0;i < getContacts().length;i++) {
            ret += getContacts()[i] + "\n";
        }

        return ret;
    }

    public static boolean saveAsTextFile(PhoneBook phonebook, String path) throws IOException {
        File f = new File(path);
        if (phonebook == null)
            return false;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(phonebook);
        }
        return false;
    }

    public static PhoneBook loadFromTextFile(String path) throws IOException, InvalidFormatException {
        File f = new File(path);
        if (!f.exists())
            throw new IOException();
        if (!f.canWrite())
            throw new InvalidFormatException();
        PhoneBook p = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            p = (PhoneBook) ois.readObject();
        } catch (ClassNotFoundException ife) {
            ife.printStackTrace();
        }
        return p;

    }

    public Contact[] getContactsForNumber(String number_prefix) {
        List<Contact> list = new ArrayList<>();
        for (Contact contact : contacts) {
            for (String s : contact.getNumbers()) {
                if (s.startsWith(number_prefix)&&!list.contains(contact)) {
                    list.add(contact);
                    continue;
                }
            }
        }
        return list.stream().toArray(Contact[]::new);
    }
}
