package jose.com.bookworm.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;

/**
 * Created by Joe on 10/10/17.
 */

@Entity
public class Book {
    @Id
    long id;

    @Index
    String title;

    @Index
    String author;

    String nameOfBorrower;
    int year;
    boolean finishedReading;
    boolean returned;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNameOfBorrower() {
        return nameOfBorrower;
    }

    public void setNameOfBorrower(String nameOfBorrower) {
        this.nameOfBorrower = nameOfBorrower;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isFinishedReading() {
        return finishedReading;
    }

    public void setFinishedReading(boolean finishedReading) {
        this.finishedReading = finishedReading;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }


}
