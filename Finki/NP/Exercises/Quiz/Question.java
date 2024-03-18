package mk.ukim.finki.NP.ZadaciZaVezbanje.Quiz;

public class Question {
    private String type;
    private String text;
    private int points;

    public Question(String type, String text, int points) {
        this.type = type;
        this.text = text;
        this.points = points;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }
}
