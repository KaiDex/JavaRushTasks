package bookdatabase.dao;

import bookdatabase.model.Book;

import java.util.List;

/**
 * Created by 100500 on 06.07.2017.
 */
public interface BookDao {

    void addBook(Book book);

    void updateBook(Book book);

    void removeBook(int id);

    Book getBookById(int id);

    List<Book> listBooks();

    List<Book> getBooksByTitle(String title);
}
