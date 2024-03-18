package mk.ukim.finki.NP.ZadaciZaVezbanje.Quiz;

public class MCQuestion extends Question {
    private char answer;

    public MCQuestion(String type, String text, int points, char answer) throws InvalidOperationException {
        super(type, text, points);
        if (answer != 'A' && answer != 'B' && answer != 'C' && answer != 'D' && answer != 'E')
            throw new InvalidOperationException(answer);
        this.answer = answer;
    }

    @Override
    public String toString() {
        return String.format("Multiple Choice Question: %s Points %d Answer: %c", getText(), getPoints(), answer);
    }

    public char getAnswer() {
        return answer;
    }
}
