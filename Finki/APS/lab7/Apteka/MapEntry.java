package mk.ukim.finki.lab7.Apteka;

public class MapEntry <K, E> {
    K key;
    E value;
    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }
    public String toString () {
        return "<" + key + "," + value + ">";
    }
}
