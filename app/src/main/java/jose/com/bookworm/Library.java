package jose.com.bookworm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import jose.com.bookworm.model.Book;


/**
 * Created by Joe on 10/29/17.
 */

public class Library extends AppCompatActivity{
    Box<Book> bookBox;
    private List<Book> allBooksList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BoxStore boxStore = ((App) getApplication()).getBoxStore();
        bookBox = boxStore.boxFor(Book.class);
        String flag = getIntent().getStringExtra("flag");
        if(flag.equals("show_all_books")){
            allBooksList = bookBox.getAll();
        }

        Observer<Book> observer = new Observer<Book>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Book book) {
                Toast.makeText(getApplicationContext(), book.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        Observable<Book> observable = Observable.fromIterable(allBooksList);
        observable.subscribe(observer);
    }
}
