package jose.com.bookworm;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import io.objectbox.Box;
import jose.com.bookworm.model.Book;
import jose.com.bookworm.model.MyObjectBox;
import jose.com.bookworm.view.BookCardView;

/**
 * Created by Joe on 10/31/17.
 */

public class BookAdapter extends RecyclerView.Adapter<BookCardView.BookViewHolder> {
    private List<Book> data;

    public BookAdapter(){
        Box<Book> bookBox = MyObjectBox.builder().androidContext(App.class).build().boxFor(Book.class);
        data = bookBox.getAll();
    }
    @Override
    public BookCardView.BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BookCardView.BookViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
