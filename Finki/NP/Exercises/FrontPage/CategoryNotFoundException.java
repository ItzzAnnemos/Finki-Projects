package mk.ukim.finki.NP.ZadaciZaVezbanje.FrontPage;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(String category) {
        super("Category " + category + " was not found");
    }
}
