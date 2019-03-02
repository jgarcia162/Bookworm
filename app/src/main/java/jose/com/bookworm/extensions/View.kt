package jose.com.bookworm.extensions

import android.view.View

fun View.onClick(action: (View) -> Unit={}){
    setOnClickListener(action)
}

fun View.onClick(listener: View.OnClickListener){
    setOnClickListener(listener)
}