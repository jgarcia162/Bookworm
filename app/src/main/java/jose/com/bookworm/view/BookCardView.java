package jose.com.bookworm.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Joe on 10/30/17.
 */

public class BookCardView extends RecyclerView.ViewHolder {

    public BookCardView(View view) {
        super(view);
    }


    public class BookViewHolder extends RecyclerView.ViewHolder {
        public BookViewHolder(View itemView) {
            super(itemView);
        }
    }
}
