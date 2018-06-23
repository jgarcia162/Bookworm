package jose.com.bookworm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import jose.com.bookworm.model.Book;
import jose.com.bookworm.view.BookViewHolder;

/**
 * Created by Joe on 10/31/17.
 */

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private List<Book> data;

    public BookAdapter(List<Book> data){
//        Box<Book> bookBox = MyObjectBox.builder().androidContext(App.class).build().boxFor(Book.class);
        this.data = data;
    }
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
//        holder.bind(data.get(position));
        holder.bind(position +"");
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public void setData(List<Book> data){
        this.data = data;
    }
}
