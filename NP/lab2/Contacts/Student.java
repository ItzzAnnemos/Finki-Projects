package mk.ukim.finki.NP.lab2.Contacts;

import java.util.Arrays;
import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private String city;
    private int age;
    private long index;
    private Contact[] contacts;
    private int size;
    private int numOfContacts;

    public Student(String firstName, String lastName, String city, int age, long index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;
        this.contacts = new Contact[1];
        this.size = 0;
        this.numOfContacts = 0;
    }

    private void resizeContactsArray() {
        int newSize = contacts.length * 2;
        contacts = Arrays.copyOf(contacts, newSize);
    }

    public void addEmailContact(String date, String email) {
        if (size == contacts.length) {
            resizeContactsArray();
        }
        contacts[size] = new EmailContact(date, email);
        size++;
        numOfContacts++;
    }

    public void addPhoneContact(String date, String phone) {
        if (size == contacts.length) {
            resizeContactsArray();
        }
        contacts[size] = new PhoneContact(date, phone);
        size++;
        numOfContacts++;
    }

    public Contact[] getEmailContacts() {
        Contact[] emails = new EmailContact[size];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(contacts[i].getType(), "Email")) {
                emails[j] = contacts[i];
                j++;
            }
        }
        return Arrays.copyOf(emails, j);
    }

    public Contact[] getPhoneContacts() {
        Contact[] phones = new PhoneContact[size];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(contacts[i].getType(), "Phone")) {
                phones[j] = contacts[i];
                j++;
            }
        }
        return Arrays.copyOf(phones, j);
    }

    public String getCity() {
        return city;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public long getIndex() {
        return index;
    }

    public Contact getLatestContact() {
        Contact newest = contacts[0];
        for (int i = 1; i < size; i++) {
            if (contacts[i].isNewerThan(newest)) {
                newest = contacts[i];
            }
        }
        return newest;
    }

    public int getNumContacts() {
        return numOfContacts;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        result.append("\"ime\":\"").append(firstName).append("\", ");
        result.append("\"prezime\":\"").append(lastName).append("\", ");
        result.append("\"vozrast\":").append(age).append(", ");
        result.append("\"grad\":\"").append(city).append("\", ");
        result.append("\"indeks\":").append(index).append(", ");
        result.append("\"telefonskiKontakti\":[");

        Contact[] phoneContacts = getPhoneContacts();
        for (int i = 0; i < phoneContacts.length; i++) {
            result.append("\"").append(((PhoneContact) phoneContacts[i]).getPhone()).append("\"");
            if (i < phoneContacts.length - 1) {
                result.append(", ");
            }
        }

        result.append("], ");
        result.append("\"emailKontakti\":[");

        Contact[] emailContacts = getEmailContacts();
        for (int i = 0; i < emailContacts.length; i++) {
            result.append("\"").append(((EmailContact) emailContacts[i]).getEmail()).append("\"");
            if (i < emailContacts.length - 1) {
                result.append(", ");
            }
        }

        result.append("]}");

        return result.toString();
    }
}
