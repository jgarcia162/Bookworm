package jose.com.bookworm

import android.view.View

/**
 * Abstract class defining methods to check Double Click where Time Delay between the two clicks is
 * set to 300 ms
 * */
abstract class DoubleClickListener : View.OnClickListener {
  private var lastClickTime: Long = 0
  
  override fun onClick(v: View?) {
    val clickTime = System.currentTimeMillis()
    if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
      onDoubleClick(v)
    }
    lastClickTime = clickTime
  }
  
  abstract fun onDoubleClick(v: View?)
  
  companion object {
    private const val DOUBLE_CLICK_TIME_DELTA: Long = 300 //milliseconds
  }
}