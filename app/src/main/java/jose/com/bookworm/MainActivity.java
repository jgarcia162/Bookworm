package jose.com.bookworm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import jose.com.bookworm.model.Book;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText titleEditText;
    private EditText authorEditText;
    private Button addBookButton;
    private Button getBookButton;
    private Box<Book> bookBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BookWorm");
        BoxStore boxStore =((App) getApplication()).getBoxStore();
        bookBox = boxStore.boxFor(Book.class);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.hasFixedSize();
        List<Book> allBooksList = bookBox.getAll();
        BookAdapter adapter = new BookAdapter(allBooksList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        titleEditText = (EditText) findViewById(R.id.title_edit_text);


        authorEditText = (EditText) findViewById(R.id.author_edit_text);


        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };

        addBookButton.setOnClickListener(listener);







    }

    void addBook(Book book) {
        bookBox.put(book);
    }

    public void showAllBooks(View view) {
        Intent intent = new Intent(getApplicationContext(),LibraryActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("key",3);
        outState.putBoolean("true",true);
        super.onSaveInstanceState(outState);




    }
}

