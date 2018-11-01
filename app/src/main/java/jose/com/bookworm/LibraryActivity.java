package jose.com.bookworm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import jose.com.bookworm.model.Book;


/**
 * Created by Joe on 10/29/17.
 */

public class LibraryActivity extends AppCompatActivity {
    private Box<Book> bookBox;
    private List<Book> allBooksList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        BoxStore boxStore = ((App) getApplication()).getBoxStore();
        bookBox = boxStore.boxFor(Book.class);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.hasFixedSize();
        allBooksList = bookBox.getAll();
        BookAdapter adapter = new BookAdapter(allBooksList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

//
//        String flag = getIntent().getStringExtra("flag");
//
//        if (flag.equals("show_all_books")) {
//            allBooksList = bookBox.getAll();
//        }

//        createObserver();
    }

//    private void createObserver() {
//        Observer<Book> observer = new Observer<Book>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Book book) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//
//        Observable<Book> observable = Observable.fromIterable(allBooksList);
//        observable.subscribe(observer);
//    }
}
