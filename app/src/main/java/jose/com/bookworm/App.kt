package jose.com.bookworm

import android.app.Application

import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import jose.com.bookworm.model.MyObjectBox

/**
 * Created by Joe on 10/23/17.
 */

class App : Application() {

  var boxStore: BoxStore? = null
    private set

  override fun onCreate() {
    super.onCreate()
    boxStore = MyObjectBox.builder()
        .androidContext(this@App)
        .build()
    if (BuildConfig.DEBUG) {
      AndroidObjectBrowser(boxStore).start(this)
    }
  }
}
