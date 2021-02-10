package jose.com.bookworm.extensions

import android.content.Context
import android.widget.Toast

/**
 * Extension function on [Context] to make "toasting" easier. Builds and displays a basic [Toast]
 * of length Toast.LENGTH_SHORT
 *
 * @param text the String to display in the toast
 * */
fun Context.toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()