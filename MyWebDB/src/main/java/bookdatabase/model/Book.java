package bookdatabase.model;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="TITLE", length = 100)
    private String title;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="AUTHOR", length = 100)
    private String author;

    @Column(name="ISBN", length = 20)
    private String isbn;

    @Column(name="PRINT_YEAR", columnDefinition = "INT")
    private String printYear;

    @Column(name="READ_ALREADY")
    private boolean readAlready;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPrintYear() {
        return printYear;
    }

    public void setPrintYear(String printYear) {
        this.printYear = printYear;
    }

    public boolean isReadAlready() {
        return readAlready;
    }

    public void setReadAlready(boolean readAlready) {
        this.readAlready = readAlready;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", printYear='" + printYear + '\'' +
                ", readAlready=" + readAlready +
                '}';
    }
}
