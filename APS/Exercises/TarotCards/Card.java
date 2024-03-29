package mk.ukim.finki.ZadaciZaVezbanje.TarotCards;

public class Card implements Comparable<Card> {
    private int id;
    private int rank;

    public Card(int id, int rank) {
        this.id = id;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public int compareTo(Card o) {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("[%d - %d]", id, rank);
    }
}
