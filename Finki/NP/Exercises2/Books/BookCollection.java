package mk.ukim.finki.NP.ZadaciZaVezbanje2.Books;

import java.util.*;
import java.util.stream.Collectors;

public class BookCollection {
    private Map<String, List<Book>> books;
    private List<Book> bookList;

    public BookCollection() {
        this.books = new HashMap<>();
        this.bookList = new ArrayList<>();
    }

    public void addBook(Book book) {
        String cat = book.getCategory().toLowerCase();
        if (books.containsKey(cat)) {
            books.get(cat).add(book);
            bookList.add(book);
        } else {
            books.put(cat, new ArrayList<>());
            books.get(cat).add(book);
            bookList.add(book);
        }
    }

    public void printByCategory(String category) {
        Comparator<Book> comparator = Comparator.comparing(Book::getTitle).thenComparing(Book::getPrice);

        List<Book> list = books.get(category.toLowerCase());

        list.sort(comparator);

        for (Book book : list) {
            System.out.println(book);
        }
    }

    public List<Book> getCheapestN(int n) {
        bookList.sort(Comparator.comparing(Book::getPrice).thenComparing(Book::getTitle));

        return bookList.stream()
                .limit(n)
                .collect(Collectors.toList());
    }
}
