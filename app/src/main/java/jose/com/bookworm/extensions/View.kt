package jose.com.bookworm.extensions

import android.view.View

fun View.onClick(action: (View) -> Unit){
    setOnClickListener(action)
}