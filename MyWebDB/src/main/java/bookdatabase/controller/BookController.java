package bookdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import bookdatabase.model.Book;
import bookdatabase.service.BookService;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

@Controller
public class BookController {

    private BookService bookService;
    protected PagedListHolder<Book> articles;
    private boolean isInitedList = false;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    private void initAllBooks() {
        if (!isInitedList) {
            isInitedList = true;
            articles = new PagedListHolder<>();
            articles.setSource(bookService.listBooks());
            articles.setPageSize(10);
        }
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        initAllBooks();
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.articles.getPageList());

        return "books";
    }

    @RequestMapping("nextPage")
    public String getNextPage() {

        articles.nextPage();

        return "redirect:books";
    }


    @RequestMapping("prevPage")
    public String getPrevPage() {

        articles.previousPage();

        return "redirect:books";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(@RequestParam("title") String title, Model model) {

        model.addAttribute("searchForTitle", title);

        return "redirect:searchbooks";
    }

    @RequestMapping(value = "searchbooks", method = RequestMethod.GET)
    public String searchBooksByTitle(@ModelAttribute("searchForTitle") String title, Model model) {

        articles = new PagedListHolder<>();
        articles.setSource(this.bookService.getBooksByTitle(title));
        articles.setPageSize(10);

        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.getBooksByTitle(title));

        return "searchbooks";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {

        if (book.getId() == 0) {
            this.bookService.addBook(book);
        } else {
            this.bookService.updateBook(book);
        }

        isInitedList = false;

        return "redirect:/books";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id) {

        this.bookService.removeBook(id);
        isInitedList = false;

        return "redirect:/books";
    }

    @RequestMapping("editbook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        Book bookById = this.bookService.getBookById(id);

        model.addAttribute("book", bookById);

        return "editbook";
    }

    @RequestMapping("/editSaved")
    public String editBookSaved(@ModelAttribute Book book) {
        isInitedList = false;
        book.setReadAlready(false);
        this.bookService.updateBook(book);

        return "redirect:/books";
    }

    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model) {
        Book bookById = this.bookService.getBookById(id);
        if (!bookById.isReadAlready()) {
            bookById.setReadAlready(true);
            this.bookService.updateBook(bookById);
        }

        isInitedList = false;

        model.addAttribute("book", bookById);

        return "bookdata";
    }


}
