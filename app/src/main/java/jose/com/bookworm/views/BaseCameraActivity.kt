package jose.com.bookworm.views

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import dagger.android.support.DaggerAppCompatActivity
import jose.com.bookworm.R
import jose.com.bookworm.extensions.onClick
import kotlinx.android.synthetic.main.activity_base_camera.*

abstract class BaseCameraActivity : DaggerAppCompatActivity(), LifecycleOwner, View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_camera)
        retry_button.setOnClickListener {
            if (camera_view.visibility == VISIBLE) showPreview() else hidePreview()
        }
        camera_view.setLifecycleOwner(this)//TODO implement lifecycle
        capture_button.onClick(this)
    }

    private fun hidePreview() {
        preview_frame.visibility = GONE
        camera_view.visibility = VISIBLE
    }

    private fun showPreview() {
        preview_frame.visibility = VISIBLE
        camera_view.visibility = GONE
    }
}
