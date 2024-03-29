package mk.ukim.finki.NP.ZadaciZaVezbanje.Quiz;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Quiz {
    private List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(String questionData) {
        String[] parts = questionData.split(";");
        if (parts[0].equals("TF")) {
            questions.add(new TFQuestion(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]));
        } else {
            try {
                questions.add(new MCQuestion(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3].charAt(0)));
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printQuiz(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        List<Question> tmp = questions;

        tmp.sort(Comparator.comparingInt(Question::getPoints).reversed());

        for (Question question : tmp) {
            pw.println(question.toString());
        }

        pw.flush();
    }

    public void answerQuiz(List<String> answers, OutputStream os) {
        if (answers.size() != questions.size()) {
            try {
                Question tmp = new MCQuestion(" ", " ", 0, 'H');
            } catch (InvalidOperationException e) {
                e.getMessage();
                System.out.println("Answers and questions must be of same length!");
            }
        } else {
            double points = 0.00;
            List<String> pointsList = new ArrayList<>();
            for (int i = 0;i < answers.size();i++) {
                if (questions.get(i) instanceof TFQuestion) {
                    if (((TFQuestion)questions.get(i)).getAnswer().equals(answers.get(i))) {
                        points += questions.get(i).getPoints();
                        pointsList.add(String.format("%.2f", questions.get(i).getPoints() * 1.00));
                    } else {
                        points += 0;
                        pointsList.add("0.00");
                    }
                } else {
                    if (((MCQuestion)questions.get(i)).getAnswer() == answers.get(i).charAt(0)) {
                        points += questions.get(i).getPoints();
                        pointsList.add(String.format("%.2f", questions.get(i).getPoints() * 1.00));
                    } else {
                        points -= (questions.get(i).getPoints() * 0.20);
                        pointsList.add(String.format("-%.2f", questions.get(i).getPoints() * 0.20));
                    }
                }
            }

            PrintWriter pw = new PrintWriter(os);
            for (int i = 0;i < pointsList.size();i++) {
                pw.println(i + 1 + ". " + pointsList.get(i));
            }
            pw.printf("Total points: %.2f", points);

            pw.flush();
        }
    }
}
