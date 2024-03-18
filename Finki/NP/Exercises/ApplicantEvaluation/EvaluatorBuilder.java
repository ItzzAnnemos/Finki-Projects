package mk.ukim.finki.NP.ZadaciZaVezbanje.ApplicantEvaluation;

public class EvaluatorBuilder {
    public static Evaluator build(Evaluator.TYPE type) throws InvalidEvaluation {
        Evaluator rez = null;

        switch (type) {
            case NO_CRIMINAL_RECORD:
                rez = a -> !a.hasCriminalRecord();
                return rez;
            case MORE_EXPERIENCE:
                rez = a -> a.getEmploymentYears() > 10;
                return rez;
            case MORE_CREDIT_SCORE:
                rez = a -> a.getCreditScore() > 500;
                return rez;
            case NO_CRIMINAL_RECORD_AND_MORE_EXPERIENCE:
                rez = a -> !a.hasCriminalRecord() && a.getEmploymentYears() > 10;
                return rez;
            case MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE:
                rez = a -> a.getEmploymentYears() > 10 && a.getCreditScore() > 500;
                return rez;
            case NO_CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE:
                rez = a -> !a.hasCriminalRecord() && a.getCreditScore() > 500;
                return rez;
            case INVALID:
                throw new InvalidEvaluation();
            default:
                throw new InvalidEvaluation();
        }
    }
}
