package mk.ukim.finki.NP.lab8.TriviaGame;

import java.util.Scanner;

public class TriviaGame {
    private final TriviaData questions;
    private int score;

    public TriviaGame() {
        questions = new TriviaData();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.addQuestion("The possession of more than two sets of chromosomes is termed?",
                "polyploidy", 3, TriviaQuestion.FREE_FORM);
        questions.addQuestion("Erling Kagge skiied into the north pole alone on January 7, 1993.",
                "F", 1, TriviaQuestion.TRUE_FALSE);
        questions.addQuestion("1997 British band that produced 'Tub Thumper'",
                "Chumbawumba", 2, TriviaQuestion.FREE_FORM);
        questions.addQuestion("I am the geometric figure most like a lost parrot",
                "polygon", 2, TriviaQuestion.FREE_FORM);
        questions.addQuestion("Generics were introducted to Java starting at version 5.0.",
                "T", 1, TriviaQuestion.TRUE_FALSE);
    }

    public void playGame() {
        Scanner keyboard = new Scanner(System.in);
        int questionNum = 0;

        while (questionNum < questions.numQuestions()) {
            questions.showQuestion(questionNum);
            String answer = keyboard.nextLine();
            validateAnswer(answer, questionNum);
            System.out.println("Your score is " + score);
            questionNum++;
        }

        System.out.println("Game over!  Thanks for playing!");
    }

    private void validateAnswer(String answer, int questionNum) {
        TriviaQuestion q = questions.getQuestion(questionNum);

        if ((q.type == TriviaQuestion.TRUE_FALSE && answer.charAt(0) == q.answer.charAt(0)) ||
                (q.type == TriviaQuestion.FREE_FORM && answer.equalsIgnoreCase(q.answer))) {
            System.out.println("That is correct!  You get " + q.value + " points.");
            score += q.value;
        } else {
            System.out.println("Wrong, the correct answer is " + q.answer);
        }
    }

    public static void main(String[] args) {
        TriviaGame game = new TriviaGame();
        game.playGame();
    }
}
