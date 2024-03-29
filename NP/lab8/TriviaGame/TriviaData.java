package mk.ukim.finki.NP.lab8.TriviaGame;

import java.util.ArrayList;

public class TriviaData {
    private final ArrayList<TriviaQuestion> data;

    public TriviaData() {
        data = new ArrayList<>();
    }

    public void addQuestion(String question, String answer, int value, int type) {
        TriviaQuestion triviaQuestion = new TriviaQuestion(question, answer, value, type);
        data.add(triviaQuestion);
    }

    public void showQuestion(int index) {
        TriviaQuestion q = data.get(index);
        System.out.println("Question " + (index + 1) + ".  " + q.value + " points.");
        System.out.println(q.question);
        if (q.type == TriviaQuestion.TRUE_FALSE) {
            System.out.println("Enter 'T' for true or 'F' for false.");
        }
    }

    public int numQuestions() {
        return data.size();
    }

    public TriviaQuestion getQuestion(int index) {
        return data.get(index);
    }
}
