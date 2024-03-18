package mk.ukim.finki.NP.lab8.TriviaGame;

public class TriviaQuestion {
    public static final int TRUE_FALSE = 0;
    public static final int FREE_FORM = 1;
    public String question;
    public String answer;
    public int value;
    public int type;

    public TriviaQuestion() {
        this("", "", 0, FREE_FORM);
    }

    public TriviaQuestion(String question, String answer, int value, int type) {
        this.question = question;
        this.answer = answer;
        this.value = value;
        this.type = type;
    }
}
