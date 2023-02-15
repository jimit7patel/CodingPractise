package java8;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookService {
    public List<Book> getSorted(){
        Comparator<Book> cmp = (b1, b2) -> b1.getName().compareTo(b2.getName());
        List<Book> books = new BookDAO().getBooks();
        Collections.sort(books, cmp);
        return books;
    }
}
