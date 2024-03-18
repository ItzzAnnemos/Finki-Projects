package mk.ukim.finki.lab2.Magic;

public class Card {
    private int type;
    private int health;
    private int magicPower;

    public Card(int type, int health, int magicPower) {
        this.type = type;
        this.health = health;
        this.magicPower = magicPower;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = magicPower;
    }

    public int significance() {
        return health * magicPower;
    }


    @Override
    public String toString() {
        return String.valueOf(type);
    }
}
