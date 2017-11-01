package jose.com.bookworm.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import jose.com.bookworm.R;
import jose.com.bookworm.model.Book;

/**
 * Created by Joe on 10/30/17.
 */

public class BookViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public BookViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.title_text_view);
    }

    public void bind(Book book) {
        textView.setText(book.getTitle());
    }
}

