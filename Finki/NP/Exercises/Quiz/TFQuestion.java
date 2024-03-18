package mk.ukim.finki.NP.ZadaciZaVezbanje.Quiz;

public class TFQuestion extends Question {
    private String answer;

    public TFQuestion(String type, String text, int points, String answer) {
        super(type, text, points);
        this.answer = answer;
    }

    @Override
    public String toString() {
        return String.format("True/False Question: %s Points: %d Answer: %s", getText(), getPoints(), answer);
    }

    public String getAnswer() {
        return answer;
    }
}
