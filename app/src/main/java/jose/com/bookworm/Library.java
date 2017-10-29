package jose.com.bookworm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import jose.com.bookworm.model.Book;

/**
 * Created by Joe on 10/29/17.
 */

public class Library extends AppCompatActivity{
    Box<Book> bookBox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BoxStore boxStore = ((App) getApplication()).getBoxStore();
        bookBox = boxStore.boxFor(Book.class);

    }
}
